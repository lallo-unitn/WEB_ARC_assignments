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
                        "class=\"cell\"" +
                        " type=\"text\" " +
                        " id=\"" + cellID + "\" " +
                        " readonly=\"readonly\"" +
                        " placeholder='" + cellArray[cellID].value + "'" +
                        " onfocus='showOnFocus(\"" + cellID + "\")'>" +
                        "</td>";
                    cellIndex++;
                }
                HTMLtoWrite = HTMLtoWrite + "</tr>";
            }
            table.innerHTML = HTMLtoWrite;
        }
    };
    xhttp.send();
    document.getElementById("formulaInput").addEventListener('keyup', (e) => {
        if (e.key === 'Enter') {
            let value = inputFormulaEl.value;
            inputFormulaEl.value = "";
            inputFormulaEl.blur();
            submit(value);

        } else {
            selectedCell.value = inputFormulaEl.value;
            console.log(inputFormulaEl + "&&");
            console.log(selectedCell.value);
            console.log(e.key);
        }
    })

    document.getElementById("formulaInput").addEventListener('focusout', (e) => {
        let value = inputFormulaEl.value;
        if (value !== "") {
            inputFormulaEl.value = "";
            submit(value);
        }
    })
    return cellsJSON;
}

function showOnFocus(cellID) {
    let cellJSON;
    let url = "GetCellServlet" +
        "?id=" + cellID;
    let xhttp = new XMLHttpRequest();
    xhttp.open("GET", url, true);
    //xhttp.responseType = "json";
    selectedCellID = cellID;
    selectedCell = document.getElementById(selectedCellID);
    xhttp.onreadystatechange = function () {
        const done = 4, ok = 200;
        if (this.readyState === done && this.status === ok) {
            cellJSON = JSON.parse(this.response);
            let inputFormulaEl = document.getElementById("formulaInput");
            if (cellJSON.formula === "") {
                inputFormulaEl.value = "=" + cellJSON.formula;
            } else {
                inputFormulaEl.value = cellJSON.formula;
            }
        }
    }
    xhttp.send();
    return cellJSON;
}

function submit(value) {
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
            for (let i = 0; i < size; i++) {
                cellID = cellsJSON.updatedCells[i].id;
                cellArray[cellID].value = cellsJSON.updatedCells[i].value;
                cellArray[cellID].formula = cellsJSON.updatedCells[i].formula;
                cellToUpdate = document.getElementById(cellID);
                cellToUpdate.value = cellArray[cellID].value;
            }
        }
    }
    selectedCell = document.getElementById(selectedCellID);
    let data = '{' +
        '"id" : "' + selectedCellID + '"' +
        ',"value" : "' + selectedCell.getAttribute("placeholder") + '"' +
        ',"formula" : "' + value + '"' +
        '}';
    xhttp.send(data);
}