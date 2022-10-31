const errInFormula = "### ERROR IN FORMULA ###";

let selectedCellID;
let selectedCell;
let inputFormulaEl;
let cellArray = [];

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
        }
    };
    xhttp.send();
    document.getElementById("formulaInput").addEventListener('keyup', (e) => {
        if (e.key === 'Enter') {
            let formula = inputFormulaEl.value;
            inputFormulaEl.value = "";
            inputFormulaEl.blur();
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
            ){
                selectedCell.value = "";
            }else{
                selectedCell.value = cellArray[selectedCellID].value;
            }
        } else {
            selectedCell.value = inputFormulaEl.value;
            console.log(inputFormulaEl + "&&");
            console.log(selectedCell.value);
            console.log(e.key);
        }
    })

    document.getElementById("formulaInput").addEventListener('focusout', (e) => {
        let formula = inputFormulaEl.value;
        inputFormulaEl.value = "";
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
        ){
            selectedCell.value = "";
        }else{
            selectedCell.value = cellArray[selectedCellID].value;
        }
        selectedCell.style.borderColor = null;
        console.log(selectedCellID);
    })
    return cellsJSON;
}

function checkIfEmpty(cellID){
    console.log("==============" + cellID + "==============");
    let cell = document.getElementById(cellID);
    let formula = cellArray[cellID].formula;
    console.log(formula);
    if(
        formula !== "" &&
        formula !== "=" &&
        formula !== "= "
    ){
        cell.value = cellArray[cellID].value;
        return true;
    } else {
        cell.value = "";
        return false;
    }
}

function checkIfError(cellID){
    let cell = document.getElementById(cellID);
    let formula = cellArray[cellID].formula;
    console.log("----------" + formula + "----------");
    if (formula.includes(errInFormula)) {
        cellArray[cellID].formula = "=" + errInFormula;
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

function submit(formula) {
    let xhttp = new XMLHttpRequest();
    let url = "UpdateSheetServlet";
    xhttp.open("POST", url, true);
    xhttp.setRequestHeader("Accept", "application/json");
    xhttp.setRequestHeader("Content-Type", "application/json");
    //xhttp.responseType = "json";
    xhttp.onreadystatechange = function () {
        const done = 4, ok = 200;
        if (this.readyState === done && this.status === ok) {
            let cellsJSON = JSON.parse(this.response);
            let size = parseInt(cellsJSON.size);
            if (size === 0) {
                selectedCell.value = cellArray[selectedCellID].value;
                return;
            }
            let cellID;
            let cellToUpdate;
            let formula;
            for (let i = 0; i < size; i++) {
                cellID = cellsJSON.updatedCells[i].id;
                cellToUpdate = document.getElementById(cellID);
                cellArray[cellID].value = cellsJSON.updatedCells[i].value;
                formula = cellsJSON.updatedCells[i].formula;
                cellArray[cellID].formula = formula;
                cellToUpdate.value = cellArray[cellID].value;
                checkIfError(cellID);
                checkIfEmpty(cellID);
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
}