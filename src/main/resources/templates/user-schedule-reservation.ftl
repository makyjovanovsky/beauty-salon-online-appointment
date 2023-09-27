<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Schedule Reservation</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <!-- Font Awesome -->
    <link
            href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css"
            rel="stylesheet"
    />
    <!-- Google Fonts -->
    <link
            href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700&display=swap"
            rel="stylesheet"
    />
    <!-- MDB -->
    <link
            href="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/5.0.0/mdb.min.css"
            rel="stylesheet"
    />
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <script>
        async function sendRequest() {
            let date = document.getElementById("date").value;
            let type = document.getElementById("type").value;
            console.log(date)
            const params = {
                "date": String(date),
                "type": String(type)
            };
            const options = {
                method: 'POST',
                body: JSON.stringify(params)
            };
            fetch('http://localhost:8080/reservation', options)
                .then(response => response.json())
                .then(response => {
                    console.log(response)
                });

        }
    </script>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <a class="navbar-brand" href="#">Salon Bella</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item ">
                <a class="nav-link" href="/userDashboard">Home <span class="sr-only">(current)</span></a>
            </li>

            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    Functions - Reservations
                </a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                    <a class="dropdown-item" href="/schedule-reservation">Schedule Reservations</a>
                    <a class="dropdown-item" href="/my-reservations">My scheduled reservations</a>
                </div>
            </li>

            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    Functions - Orders
                </a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                    <a class="dropdown-item" href="/order">Order Products</a>
                    <a class="dropdown-item" href="/my-orders">Ordered Products</a>
                </div>
            </li>

        </ul>
        <form class="form-inline my-2 my-lg-0" method="get" action="/logout">
            <button class="btn btn-outline-info my-2 my-sm-0" type="submit">Log out</button>
        </form>
    </div>
</nav>
<br>
<div class="h-100 d-flex align-items-center justify-content-center">
    <form method="post" action="/get-free-reservations">
        <#if date??>
            <div class="d-flex justify-content-center">
                <input type="date" min="" name="date" id="date" value="${date}" required/>
            </div>
        <#else>
            <div class="d-flex justify-content-center">
                <input type="date" min="" name="date" id="date" required/>
            </div>
        </#if>

        <br>
        <div class="d-flex justify-content-center">
            <select name="type" id="type" required>
                <option value="NAILS">NAILS</option>
                <option value="MAKEUP">MAKEUP</option>
                <option value="WAXING">WAXING</option>
            </select>
        </div>
        <br>
        <button class="btn btn-primary" type="submit">Get Free Reservations</button>
    </form>
</div>

<br>
<div class="h-100 d-flex align-items-center justify-content-center">
    <#if tip??>

        <form method="post" action="/make-reservation">
            <div class="d-flex justify-content-center">
                <input type="date" name="date" value="${date}" hidden/>
            </div>

            <div class="d-flex justify-content-center">
                <select name="type" id="type" hidden>
                    <option value="NAILS">NAILS</option>
                    <option value="MAKEUP">MAKEUP</option>
                    <option value="WAXING">WAXING</option>
                </select>
            </div>

            <div class="d-flex justify-content-center">
                <#if free_reservations??>
                    <select name="time" id="time">
                        <#list free_reservations as reservation>
                            <option value="${reservation}">${reservation}</option>
                        </#list>
                    </select>
                </#if>
            </div>
            <br>
            <button class="btn btn-primary" type="submit">Reserve</button>
        </form>
    </#if>
</div>


<#if tip??>
    <script>
        let array = document.getElementsByTagName("option");
        for (let i = 0; i < array.length; i++) {
            if (array[i].value == "${tip}") {
                array[i].setAttribute("selected", "");
            }
        }
    </script>
</#if>
<script>
    document.getElementById("date").setAttribute("min", new Date().toISOString().split('T')[0])
</script>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.7/dist/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
<script
        type="text/javascript"
        src="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/5.0.0/mdb.min.js"
></script>
</body>
</html>