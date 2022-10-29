function getJson() {
    var cellsJSON;
    var url = "GetValuesServlet";
    var xhttp = new XMLHttpRequest();
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
                    HTMLtoWrite =
                        HTMLtoWrite +
                        "<td>" +
                        "<input " +
                        "class=\"cell\"" +
                        " type=\"text\" " +
                        " id=\"(cellsJSON.cells[cellIndex]).id\" " +
                        " placeholder=\"" + cellsJSON.cells[cellIndex].value + "\"" +
                        " readonly=\"readonly\"" +
                        " onclick=\"showOnInput(" + cellsJSON.cells[cellIndex] + ")\">"+
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

function showOnInput(cell){
    document.getElementById("formulaInput").setAttribute("placeholder", cell.value);
}