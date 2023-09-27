<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Register Page</title>
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
</head>
<body>

<!-- Section: Design Block -->
<section class="text-center text-lg-start">
    <style>
        .cascading-right {
            margin-right: -50px;
        }

        @media (max-width: 991.98px) {
            .cascading-right {
                margin-right: 0;
            }
        }
    </style>

    <!-- Jumbotron -->
    <div class="container py-4">
        <div class="row g-0 align-items-center">
            <div class="col-lg-6 mb-5 mb-lg-0">
                <div class="card cascading-right" style="
            background: hsla(0, 0%, 100%, 0.55);
            backdrop-filter: blur(30px);
            ">
                    <div class="card-body p-5 shadow-5 text-center">
                        <h2 class="fw-bold mb-5">Sign up now</h2>
                        <form method="post" action="/register">
                            <!-- 2 column grid layout with text inputs for the first and last names -->
                            <div class="row">
                                <div class="col-md-6 mb-4">
                                    <div class="form-outline">
                                        <input type="text" id="form3Example1" class="form-control" name="name"/>
                                        <label class="form-label" for="form3Example1">First name</label>
                                    </div>
                                </div>
                                <div class="col-md-6 mb-4">
                                    <div class="form-outline">
                                        <input type="text" id="form3Example2" class="form-control" name="surname"/>
                                        <label class="form-label" for="form3Example2">Last name</label>
                                    </div>
                                </div>
                            </div>


                            <!-- Email input -->
                            <div class="form-outline mb-4">
                                <input type="text" id="form3Example3" class="form-control" name="number"/>
                                <label class="form-label" for="form3Example3">Phone Number</label>
                            </div>

                            <!-- Email input -->
                            <div class="form-outline mb-4">
                                <input type="email" id="form3Example3" class="form-control" name="email"/>
                                <label class="form-label" for="form3Example3">Email address</label>
                            </div>

                            <div class="form-outline mb-4">
                                <input type="text" id="form3Example3" class="form-control" name="username"/>
                                <label class="form-label" for="form3Example3">Username</label>
                            </div>

                            <!-- Password input -->
                            <div class="form-outline mb-4">
                                <input type="password" id="form3Example4" class="form-control" name="password"/>
                                <label class="form-label" for="form3Example4">Password</label>
                            </div>

                            <div class="form-check d-flex justify-content-center mb-4">
                                <#if email??>
                                    <p>${email}</p>
                                </#if>
                                <#if username??>
                                    <p>${username}</p>
                                </#if>
                            </div>

                            <!-- Submit button -->
                            <button type="submit" class="btn btn-primary btn-block mb-4">
                                Sign up
                            </button>

                        </form>
                    </div>
                </div>
            </div>

            <div class="col-lg-6 mb-5 mb-lg-13">
                <img src="https://i.ibb.co/jk8FCT0/background.jpg" class="w-100 rounded-4 shadow-4"
                     alt="" />
            </div>
        </div>
    </div>
    <!-- Jumbotron -->
</section>
<!-- Section: Design Block -->



<!-- MDB -->
<script
        type="text/javascript"
        src="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/5.0.0/mdb.min.js"
></script>

</body>
</html>