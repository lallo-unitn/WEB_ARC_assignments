let selectedCellID;
let selectedCell;
let inputFormulaEl;

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
                    let parameter = cellsJSON.cells[cellIndex].id;
                    HTMLtoWrite =
                        HTMLtoWrite +
                        "<td>" +
                        "<input " +
                        "class=\"cell\"" +
                        " type=\"text\" " +
                        " id=\"" + parameter + "\" " +
                        " readonly=\"readonly\"" +
                        " placeholder='" + cellsJSON.cells[cellIndex].value + "'" +
                        " onfocus='showOnFocus(\"" + parameter + "\")'>" +
                        "</td>";
                    cellIndex++;
                }
                HTMLtoWrite = HTMLtoWrite + "</tr>";
            }
            table.innerHTML = HTMLtoWrite;
        }
    };
    xhttp.send();
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
            inputFormulaEl.value = "=" + cellJSON.formula;
        }
    }
    xhttp.send();
    return cellJSON;
}

function submit(value){
    let xhttp = new XMLHttpRequest();
    let url = "UpdateSheetServlet";
    xhttp.open("POST", url, true);
    xhttp.setRequestHeader("Accept", "application/json");
    xhttp.setRequestHeader("Content-Type", "application/json");
    //xhttp.responseType = "json";
    xhttp.onreadystatechange = function () {
        const done = 4, ok = 200;
        if (this.readyState === done && this.status === ok) {
            console.log("Response");
        }
    }
    selectedCell = document.getElementById(selectedCellID);
    let data = '{' +
        '"id" : ' + selectedCellID +
        ',"value" : ' + selectedCell.getAttribute("placeholder") +
        ',"formula" : ' + value +
        '}';
    xhttp.send(data);
}