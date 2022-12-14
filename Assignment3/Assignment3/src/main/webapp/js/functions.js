const errInFormula = "### ERROR IN FORMULA ###";

let selectedCellID = null;
let selectedCell = null;
let inputFormulaEl = null;
let cellArray = [];
let lastModTimeClient = 0;
let pollingInterval = null;

//setInterval(myTimer, 1000);

function myTimer() {
    const date = new Date();
    document.getElementById("demo").innerHTML = date.toLocaleString();
}

function init() {
    inputFormulaEl = document.getElementById("formulaInput");
    let cellsJSON;
    let url = "InitServlet";
    let xhttp = new XMLHttpRequest();
    xhttp.open("GET", url, true);
    //xhttp.responseType = "json";
    xhttp.onreadystatechange = function () {
        const done = 4, ok = 200;
        if (this.readyState === done && this.status === ok) {
            cellsJSON = JSON.parse(this.response);
            let table = document.getElementById("table");
            let cellIndex = 0;
            let HTMLtoWrite = "";
            for (let col in "ABCD") {
                HTMLtoWrite = HTMLtoWrite + "<tr>";
                for (let row = 1; row <= 4; row++) {
                    let cellID = cellsJSON.cells[cellIndex].id;
                    cellArray[cellID] =
                        {
                            id: cellID,
                            formula: cellsJSON.cells[cellIndex].formula,
                            value: cellsJSON.cells[cellIndex].value
                        };
                    HTMLtoWrite =
                        HTMLtoWrite +
                        "<td>" +
                        "<input " +
                        " class=\"cell\"" +
                        " type=\"text\" " +
                        " id=\"" + cellID + "\" " +
                        " readonly=\"readonly\"" +
                        " onfocus='onCellFocus(\"" + cellID + "\")'>" +
                        "</td>";
                    cellIndex++;
                }
                HTMLtoWrite = HTMLtoWrite + "</tr>";
            }
            table.innerHTML = HTMLtoWrite;
            cellIndex = 0;
            for (let col in "ABCD") {
                for (let row = 1; row <= 4; row++) {
                    checkIfError(cellsJSON.cells[cellIndex].id);
                    checkIfEmpty(cellsJSON.cells[cellIndex].id);
                    cellIndex++;
                }
            }
            lastModTimeClient = cellsJSON.lastModTimeServer;
            pollingInterval = setInterval(statePolling, 5000);
        }
    };
    xhttp.send();

    document.getElementById("formulaInput").addEventListener('focus', (e) => {
        if (selectedCell !== null) {
            inputFormulaEl.value = cellArray[selectedCellID].formula;
            selectedCell.value = cellArray[selectedCellID].formula;
        }
    })

    document.getElementById("formulaInput").addEventListener('keyup', (e) => {
        if (e.key === 'Enter') {
            let formula = inputFormulaEl.value;
            inputFormulaEl.value = "";
            inputFormulaEl.blur();
            submit_wrapper(formula);
        } else {
            selectedCell.value = inputFormulaEl.value;
        }
    })

    document.getElementById("formulaInput").addEventListener('focusout', (e) => {
        let formula = inputFormulaEl.value;
        inputFormulaEl.value = "";
        submit_wrapper(formula);
        selectedCell.style.borderColor = null;
    })
}

function checkIfEmpty(cellID) {
    console.log("==============" + cellID + "==============");
    let cell = document.getElementById(cellID);
    let formula = cellArray[cellID].formula;
    console.log(formula);
    if (
        formula !== "" &&
        formula !== "=" &&
        formula !== "= "
    ) {
        cell.value = cellArray[cellID].value;
        return true;
    } else {
        cell.value = "";
        return false;
    }
}

function checkIfError(cellID) {
    let cell = document.getElementById(cellID);
    let formula = cellArray[cellID].formula;
    console.log("----------FORMULA----------");
    console.log(formula);
    console.log("--------END FORMULA--------");
    if (formula.includes(errInFormula)) {
        cell.style.background = "#c41414"
        return true;
    } else {
        cellArray[cellID].formula = formula;
        cell.style.background = null;
        return false;
    }
}


function onCellFocus(cellID) {
    selectedCellID = cellID;
    selectedCell = document.getElementById(selectedCellID);
    selectedCell.style.borderColor = "green";
    selectedCell.value = cellArray[selectedCellID].formula;
    let cellObj = cellArray[selectedCellID];
    inputFormulaEl = document.getElementById("formulaInput");
    inputFormulaEl.focus();
    if (cellObj.formula === "") {
        inputFormulaEl.value = "=" + cellObj.formula;
    } else {
        inputFormulaEl.value = cellObj.formula;
    }
}

function submit_wrapper(formula) {
    if (
        formula !== "" &&
        formula !== "=" &&
        formula !== cellArray[selectedCellID].formula &&
        !formula.includes(errInFormula)
    ) {
        submit(formula);
    } else if (
        formula === "" ||
        formula === "="
    ) {
        selectedCell.value = "";
    } else {
        selectedCell.value = cellArray[selectedCellID].value;
    }
}

function submit(formula) {
    let xhttp = new XMLHttpRequest();
    let url = "ChangeStateServlet";
    xhttp.open("POST", url, true);
    xhttp.setRequestHeader("Accept", "application/json");
    xhttp.setRequestHeader("Content-Type", "application/json");
    //xhttp.responseType = "json";
    xhttp.onreadystatechange = function () {
        const done = 4, ok = 200;
        if (this.readyState === done && this.status === ok) {
            let json = JSON.parse(this.response);
            let size = parseInt(json.size);
            console.log("Size = " + size);
            if (size === 0) {
                cellArray[selectedCellID].value = 0;
                cellArray[selectedCellID].formula = formula;
                selectedCell.value = cellArray[selectedCellID].value;
                selectedCell.formula = cellArray[selectedCellID].formula;
                checkIfError(selectedCellID);
                checkIfEmpty(selectedCellID);
            } else {
                if (json.lastModTimeServer !== null && json.lastModTimeServer !== undefined && json.lastModTimeServer !== "undefined") {
                    lastModTimeClient = json.lastModTimeServer;
                }
                let cellID;
                let cellToUpdate;
                let formula;
                for (let i = 0; i < size; i++) {
                    cellID = json.updatedCells[i].id;
                    cellToUpdate = document.getElementById(cellID);
                    cellArray[cellID].value = json.updatedCells[i].value;
                    formula = json.updatedCells[i].formula;
                    cellArray[cellID].formula = formula;
                    cellToUpdate.value = cellArray[cellID].value;
                    checkIfError(cellID);
                    checkIfEmpty(cellID);
                }
            }
        }
    }
    selectedCell = document.getElementById(selectedCellID);
    let cellObj = cellArray[selectedCellID];
    if (formula.includes(errInFormula)) {
        formula.replace(errInFormula, "");
    }
    let data = '{' +
        '"id" : "' + selectedCellID + '"' +
        ',"value" : "' + cellObj.value + '"' +
        ',"formula" : "' + formula + '"' +
        '}';
    xhttp.send(data);
    console.log("==============SUBMIT==============");
    console.log(data);
    console.log("============END SUBMIT============");
}

function statePolling() {
    console.log("refreshing");
    let xhttp = new XMLHttpRequest();
    let url = "PollingServlet";
    xhttp.open("POST", url, true);
    xhttp.setRequestHeader("Accept", "application/json");
    xhttp.setRequestHeader("Content-Type", "application/json");
    //xhttp.responseType = "json";
    xhttp.onreadystatechange = function () {
        const done = 4, ok = 200;
        let cellToUpdate;
        let formula;
        if (this.readyState === done && this.status === ok) {
            let json = JSON.parse(this.response);
            lastModTimeClient = json.lastModTimeServer;
            if (json.empty === "false") {
                let cellIndex = 0;
                for (let col in "ABCD") {
                    for (let row = 1; row <= 4; row++) {
                        let cellID = json.cells[cellIndex].id;
                        cellArray[cellID] =
                            {
                                id: cellID,
                                formula: json.cells[cellIndex].formula,
                                value: json.cells[cellIndex].value
                            };
                        cellToUpdate = document.getElementById(cellID);
                        formula = json.cells[cellIndex].formula;
                        if (formula.includes(errInFormula)) {
                            cellArray[cellID].value = 0;
                        }
                        cellArray[cellID].formula = formula;
                        cellToUpdate.value = cellArray[cellID].value;
                        cellIndex++;
                    }
                }
                cellIndex = 0;
                for (let col in "ABCD") {
                    for (let row = 1; row <= 4; row++) {
                        checkIfError(json.cells[cellIndex].id);
                        checkIfEmpty(json.cells[cellIndex].id);
                        cellIndex++;
                    }
                }
            }
        }
    }
    let timeStamp = '{' +
        '"lastModTimeClient": "' + lastModTimeClient + '"' +
        '}';
    console.log(timeStamp)
    xhttp.send(timeStamp);
}