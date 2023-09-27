<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Admin Page - Scheduled Reservations</title>
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
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <a class="navbar-brand" href="#">Salon Bella</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item ">
                <a class="nav-link" href="/adminDashboard">Home <span class="sr-only">(current)</span></a>
            </li>

            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown"
                   aria-haspopup="true" aria-expanded="false">
                    Functions - Reservations
                </a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                    <a class="dropdown-item" href="/admin-scheduled-reservations">Scheduled Reservations</a>
                    <a class="dropdown-item" href="/admin-schedule-reservation">Schedule reservation</a>
                    <div class="dropdown-divider"></div>
                    <a class="dropdown-item" href="/admin-get-blocked-reservations">Blocked reservations</a>
                    <a class="dropdown-item" href="/admin-block-reservation">Block reservation</a>
                </div>
            </li>

            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown"
                   aria-haspopup="true" aria-expanded="false">
                    Functions - Orders
                </a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                    <a class="dropdown-item" href="/admin-get-orders">Ordered Products</a>
                    <a class="dropdown-item" href="/admin-add-product">Add product</a>
                    <a class="dropdown-item" href="/admin-remove-product">Remove product</a>
                </div>
            </li>

        </ul>
        <form class="form-inline my-2 my-lg-0" method="get" action="/logout">
            <button class="btn btn-outline-info my-2 my-sm-0" type="submit">Log out</button>
        </form>
    </div>
</nav>
<br>

<table class="table table-striped">
    <thead>
    <tr>
        <th scope="col">Name/Surname</th>
        <th scope="col">Date/Time</th>
        <th scope="col">Type</th>
        <th scope="col">Valid</th>
        <th scope="col">Cancel</th>
    </tr>
    </thead>
    <tbody>
    <#if reservations??>
        <#list reservations as res>
            <tr>
                <td>${res['name']} ${res['surname']}</td>
                <td>${res['localDateTime']}</td>
                <td>${res['type']}</td>
                <td id="valid" class="temp">${res['valid']?string("TRUE","FALSE")}</td>
                <td>
                    <form method="post" action="/admin-cancel-reservation">
                        <input type="text" value="${res['id']}" name="id" hidden/>
                        <input type="text" value="${date}" name="date" hidden/>
                        <input type="text" value="${type}" name="type" hidden/>
                        <button type="submit" class="btn btn-primary">Cancel Reservation</button>
                    </form>
                </td>
            </tr>
        </#list>
    </#if>
    </tbody>
</table>
<script>
    let element = document.getElementsByClassName("temp")
    for (let i = 0; i < element.length; i++) {
        if (element[i].innerText === "TRUE") {
            element[i].style.color = 'blue'
        } else {
            element[i].style.color = 'red'
        }
    }

</script>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.7/dist/umd/popper.min.js"
        integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/js/bootstrap.min.js"
        integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
        crossorigin="anonymous"></script>
<script
        type="text/javascript"
        src="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/5.0.0/mdb.min.js"
></script>
</body>
</html>