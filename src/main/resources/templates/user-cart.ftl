<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>My Cart</title>
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

    <style>
        @media (min-width: 1025px) {
            .h-custom {
                height: 100vh !important;
            }
        }

        .card-registration .select-input.form-control[readonly]:not([disabled]) {
            font-size: 1rem;
            line-height: 2.15;
            padding-left: .75em;
            padding-right: .75em;
        }

        .card-registration .select-arrow {
            top: 13px;
        }

        .bg-grey {
            background-color: #eae8e8;
        }

        @media (min-width: 992px) {
            .card-registration-2 .bg-grey {
                border-top-right-radius: 16px;
                border-bottom-right-radius: 16px;
            }
        }

        @media (max-width: 991px) {
            .card-registration-2 .bg-grey {
                border-bottom-left-radius: 16px;
                border-bottom-right-radius: 16px;
            }
        }
    </style>
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
                <a class="nav-link" href="/userDashboard">Home <span class="sr-only">(current)</span></a>
            </li>

            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown"
                   aria-haspopup="true" aria-expanded="false">
                    Functions - Reservations
                </a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                    <a class="dropdown-item" href="/schedule-reservation">Schedule Reservations</a>
                    <a class="dropdown-item" href="/my-reservations">My scheduled reservations</a>
                </div>
            </li>

            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown"
                   aria-haspopup="true" aria-expanded="false">
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

<section class="h-100 h-custom">
    <div class="container py-5 h-100">
        <div class="row d-flex justify-content-center align-items-center h-100">
            <div class="col-12">
                <div class="card card-registration card-registration-2" style="border-radius: 15px;">
                    <div class="card-body p-0">
                        <div class="row g-0">
                            <div class="col-lg-8">
                                <div class="p-5">
                                    <div class="d-flex justify-content-between align-items-center mb-5">
                                        <h1 class="fw-bold mb-0 text-black">Shopping Cart</h1>
                                        <h6 class="mb-0 text-muted"></h6>
                                    </div>
                                    <hr class="my-4">
                                    <form method="post" action="/order">
                                        <#if products ??>
                                            <#list products as product>
                                                <div class="row mb-4 d-flex justify-content-between align-items-center">
                                                    <div class="col-md-2 col-lg-2 col-xl-2">
                                                        <img
                                                                src="data:image/jpeg;base64,${product['base64']}"
                                                                class="img-fluid rounded-3" alt="Cotton T-shirt">
                                                    </div>
                                                    <div class="col-md-3 col-lg-3 col-xl-3">
                                                        <h6 class="text-muted">${product['category']}</h6>
                                                        <h6 class="text-black mb-0">${product['name']}</h6>
                                                    </div>
                                                    <div class="col-md-3 col-lg-3 col-xl-3">
                                                        <h6 class="text-muted">Quantity</h6>
                                                        <input id="form1" min="0" name="${product['id']}" value="1" type="number"
                                                               class="form-control form-control-sm" onclick="myFunction()"/>
                                                    </div>
                                                    <div class="col-md-3 col-lg-3 col-xl-3">
                                                        <h6 class="text-muted">Product Price</h6>
                                                        <h6 class="mb-0" name="price" >${product['price']} ден.</h6>
                                                    </div>
                                                    <div class="col-md-1 col-lg-1 col-xl-1 text-end">
                                                        <a href="/delete-product-from-cart?id=${product['id']}" class="text-muted">
                                                            <i class="fas fa-times"></i>
                                                        </a>
                                                    </div>
                                                </div>
                                                <hr class="my-4">
                                            </#list>
                                        </#if>



                                </div>
                            </div>
                            <div class="col-lg-4 bg-grey">
                                <div class="p-5">
                                    <h3 class="fw-bold mb-5 mt-2 pt-1">Summary</h3>
                                    <hr class="my-4">

                                    <div class="d-flex justify-content-between mb-5">
                                        <h5 class="text-uppercase">Total price</h5>
                                        <h5 id="total-price"></h5>
                                    </div>

                                    <button type="submit" class="btn btn-dark btn-block btn-lg" data-mdb-ripple-color="dark">Order</button>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<script>
    let prices = document.getElementsByName("price")
    let quantities = document.getElementsByClassName("form-control form-control-sm")
    let total = 0
    for(let i=0;i<prices.length;i++) {
        total += (parseInt(prices[i].innerText.split(" ")[0]) * parseInt(quantities[i].value))
    }

    document.getElementById("total-price").innerText = total + " ден."

    function myFunction() {
        let prices = document.getElementsByName("price")
        let quantities = document.getElementsByClassName("form-control form-control-sm")
        let total = 0
        for(let i=0;i<prices.length;i++) {
            total += (parseInt(prices[i].innerText.split(" ")[0]) * parseInt(quantities[i].value))
        }
        document.getElementById("total-price").innerText = total + " ден."
    }
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