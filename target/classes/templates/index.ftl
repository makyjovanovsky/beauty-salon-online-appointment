<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Salon Bella</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/@fortawesome/fontawesome-free@6.1.2/css/fontawesome.min.css" integrity="sha384-X8QTME3FCg1DLb58++lPvsjbQoCT9bp3MsUU3grbIny/3ZwUJkRNO8NPW6zqzuW9" crossorigin="anonymous">
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
            font-family: "Abel", sans-serif;
            font-size: 10px;
            scroll-behavior: smooth;
        }
        .wrapper {
            width: auto;
            height: 100vh;
            background-image: linear-gradient( rgba(10, 20, 30, 40.5), rgba(50, 0, 20, 0.5)), url("https://i.ibb.co/jk8FCT0/background.jpg");
            background-position: center;
            background-size: cover;
            background-repeat: no-repeat;
            backdrop-filter: opacity(80%);
        }
        .Container {
            width: 100%;
            height: 100%;
            display: flex;
            justify-content: center;
            align-items: center;
        }
        .nav {
            position: fixed;
            top: 0;
            left: 0;
            width: 100%;
            height: 80px;
            border-bottom: 1px solid rgba(255, 255, 255, 0.521);
            display: flex;
            justify-content: space-between;
            align-items: center;
            padding: 0 50px;
        }
        .logo {
            font-family: "Abel", sans-serif;
            font-size: 2.5rem;
            font-weight: 600;
            letter-spacing: 0.7rem;
            color: white;
            margin: 4%;
        }
        .menu {
            display: inline-block;
            line-height: 80px;
        }
        .menu ul {
            list-style: none;
            /* display: flex;
              flex-direction: row;
              justify-content: center;
              align-items: center; */
        }
        .menu ul li {
            display: inline-block;
        }
        .menu ul li a {
            text-decoration: none;
            font-family: cursive;
            font-size: 1.2rem;
            font-weight: 600;
            letter-spacing: 0.1rem;
            color: white;
            border: 1px solid transparent;
            border-radius: 4px;
            padding: 10px 15px;
            margin: 0 5px;
            transition: 0.5s ease;
        }
        .menu ul li a:hover {
            border-color: white;
        }
        .menu ul li:nth-child(5) a {
            color: #fff200;
            border: 1px solid #fff200;
        }
        .menu ul li:nth-child(5) a:hover {
            color: black;
            background-color: #fff200;
        }
        .header {
            text-align: center;

        }
        .header h1 {
            font-family: cursive;
            font-size: 4rem;
            font-weight: 600;
            letter-spacing: 0.2rem;
            color: white;
            padding: 45% 20px 8px;
        }
        .header p {
            font-family: cursive;
            font-size: 1.5rem;
            font-weight: 600;
            letter-spacing: 0.2rem;
            color: white;
            padding: 10px 15px;
        }
        button {
            font-size: 1.5rem;
            font-weight: 600;
            letter-spacing: 0.15rem;
            color: black;
            background-color: #fff200;
            padding: 20px 30px;
            margin: 50px 5px 0;
            border: none;
            cursor: pointer;
        }

    </style>
</head>
<body>
<div class="wrapper" >
    <div class="Container">
        <div class="nav">
            <div class="logo">
                B
            </div>
            <div class="menu">
                <ul class="navMenu">
                    <li><a href="/login">Login</a></li>
                    <li><a href="/register">Register</a></li>
                </ul>
            </div>
        </div>
        <div class="header">
            <h1>Beauty Salon Bella</h1>
            <p>Kumanovo,North Macedonia</p>
        </div>
    </div>
</div>
</body>
</html>