<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <!-- Import the Anek Bangla font from Google Fonts -->
    <link href="https://fonts.googleapis.com/css2?family=Anek+Bangla:wght@400;700&display=swap" rel="stylesheet">
    <style>
        body {
            margin: 0;
            font-family: 'Anek Bangla'; /* Apply Anek Bangla font globally */
        }

        html {
            background-color: #959595;
        }

        .navbar {
            display: grid;
            grid-template-columns: repeat(10, 1fr);
            background-color: #4e60ff;
            margin: 0;
            padding: 0;
        }

        .content1 {
            grid-column-start: 1;
            grid-column-end: 2;
            grid-row-start: 1;
            grid-row-end: 2;
            padding: 20px;
            text-align: center;
        }

        .content1 > a {
            color: white;
        }

        .content2 {
			    grid-column-start: 2;
                grid-column-end: 10;
                text-align: center;
                align-content: center;         
        }

        .content2 > a {
            color: white; /* Change font color to white */
        }

        .content3 {
            grid-column-start: 10;
            grid-column-end: 11;
            grid-row-start: 1;
            grid-row-end: 2;
            padding: 20px;
            text-align: center;
        }

        .content3 > a {
            color: white;
        }

        .box {
            max-width: 500px;
            margin: 5rem auto;
            background: #ffffff;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
            padding: 2rem;
            position: relative;
        }

        .box h1 {
            font-size: 24px;
            font-weight: bold;
            margin-bottom: 0.5rem;
            text-align: center;
        }

        .form-grid label {
            margin-bottom: 3rem;
            display: block;
            text-align: center;
        }

        .confirm-button {
            background-color: #4e60ff;
            color: white;
            font-weight: bold;
            font-size: 16px;
            border: none;
            border-radius: 20px;
            padding: 0.8rem 1.5rem;
            cursor: pointer;
            display: block;
            margin: 0 auto;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.2);
            transition: all 0.2s ease-in-out;
            font-family: 'Anek Bangla';
        }

        .confirm-button:hover {
            background-color: #f44336;
            font-family: 'Anek Bangla';
            
        }

        .close-button {
            position: absolute;
            top: 1rem;
            right: 1rem;
            background-color: #4e60ff;
            color: white;
            border: none;
            border-radius: 50%;
            width: 30px;
            height: 30px;
            font-size: 18px;
            font-weight: bold;
            cursor: pointer;
            display: flex;
            align-items: center;
            justify-content: center;
        }

        .close-button {
            background-color: #4e60ff;
        }
    </style>
    <title>Admin Dashboard</title>
</head>

<body>
    <header>
        <div class="navbar">
            <div class="content1">
                <a href="teacherdashboardnew.html"><b>STUDYBUDDY</b></a>
            </div>

            <div class="content2">
                <a>Welcome back, Admin<b></b></a>
            </div>

            <div class="content3">
                <a><b>LOGOUT</b></a>
            </div>
        </div>
    </header>
    <div class="box">
        <button class="close-button">&times;</button>
        <h1>Delete User</h1>
        <div class="form-grid">
            <div>
                <label for="name">Are you sure?</label>
            </div>
        </div>
        <form action="deleteuser" method="POST">
	    <input type="hidden" name="del" value=${status.index}>
        <button class="confirm-button">CONFIRM</button>
        </form>
    </div>  
</body>
</html>
