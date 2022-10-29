<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <title>Spread Sheet</title>
    <link rel="stylesheet" href="css/spreadSheetStyle.css" />
    <!-- CSS only -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
    <script src="js/functions.js"></script>
</head>

<body onLoad="getJson()" style="background-color: black; color: bisque;">

<input class="formulaInput" id="formulaInput">
<div id="tableWrapper">
    <table id="table"></table>
</div>

</body>

</html>
