<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Spread Sheet</title>
    <script>
        function getJson() {
            var my_JSON_object;
            var url="https://mdn.github.io/learning-area/javascript/oojs/json/superheroes.json";
            var xhttp = new XMLHttpRequest();
            xhttp.open("GET", url, true);
            //xhttp.responseType = "json";
            xhttp.onreadystatechange = function () {
                var done = 4, ok = 200;
                if (this.readyState === done && this.status === ok){
                    my_JSON_object = this.response;
                    document.getElementById("myPar".innerHTML=
                        my_JSON_object.squadName;
                }
            };
            xhttp.send();
            return my_JSON_object;
        }
    </script>
</head>
<body>
</body>
</html>
