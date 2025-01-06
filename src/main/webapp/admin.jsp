<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <link href='https://fonts.googleapis.com/css?family=Anek+Bangla' rel='stylesheet'>
        <style>
            body {
                margin: 0;
                font-family: 'Anek Bangla'; /* Apply Anek Bangla globally */
            }

            html {
                background-color: white;
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
                color: white;
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

            /* navbar styling ends here */
            .bodycontent {
                max-width: 1200px;
                margin: 2rem auto;
                padding: 2rem;
                background: rgb(255, 255, 255);
                border-radius: 10px;
                box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
            }

            .bodycontent > h1 {
                font-size: 30px;
                padding: 0 0 20px 0;
                margin: auto;
            }

            .admin-title {
                display: flex;
                align-items: center;
                margin-bottom: 1.5rem;
                font-weight: bold;
            }

            .plus-icon {
                background-color: #4cd137;
                color: white;
                width: 24px;
                height: 24px;
                border-radius: 50%;
                display: inline-flex;
                align-items: center;
                justify-content: center;
                margin-left: 0.5rem;
                font-size: 1.2rem;
                cursor: pointer;
                text-decoration: none;
            }

            .greybox {
                background-color: #e0e0e0;
                margin: 0;
                padding: 20px;
                border-radius: 10px;
            }

            .admin-item {
                background-color: #f8f9fa;
                padding: 1rem;
                border-radius: 60px;
                margin-bottom: 1rem;
                display: grid;
                grid-template-columns: 200px 170px 250px 200px 100px;
                align-items: center;
                gap: 1rem;
            }

            .username-detail {
                color: rgb(51, 132, 252);
            }

            .admin-action {
                display: flex;
                gap: 0.5rem;
                justify-content: flex-end;
            }

            .delete-btn {
                background-color: #ff4757;
                color: white;
                border: none;
                border-radius: 50%;
                width: 30px;
                height: 30px;
                cursor: pointer;
                text-align: center;
                text-decoration: none;
            }

            .edit-btn {
                background-color: #4B5EE4;
                color: white;
                border: none;
                border-radius: 50%;
                width: 30px;
                height: 30px;
                cursor: pointer;
                text-align: center;
                justify-content: flex-end;
                text-decoration: none;
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

        <div class="admin-container">
            <div class="bodycontent">
                <h1 class="admin-title">
                    USER MANAGEMENT 
                    <a href="adduser.jsp" class="plus-icon">+</a>
                </h1>

                <div class="greybox">
                    
                        
                    </div>
                </div>
            </div>
        
    </body>
</html>

