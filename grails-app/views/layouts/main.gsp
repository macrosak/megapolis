<html>
    <head>
        <title><g:layoutTitle default="Grails" /></title>
        %{-- <link rel="stylesheet" href="${resource(dir:'css',file:'main.css')}" /> --}%
        <link rel="shortcut icon" href="${resource(dir:'images',file:'favicon.ico')}" type="image/x-icon" />

        <g:javascript library="application" />
        <g:javascript library="prototype" />
      <g:layoutHead />

    <style type="text/css">
      body { width: 750px; }

      body, p, table, div {font-family:verdana}

      ul.menu { margin:0; padding:0; list-style-type: none; width:100%; font-family: verdana; background-color: #fdca00; }
      .menu li { margin:0; padding:0; float: left; text-align:center; margin-right:10px ;}

      .menu a { color: black; background-color: #fdca00; display: block; padding:5px; width:100%; /* border: 1px solid #d29e00;*/ text-decoration: none;}
      .menu a:hover { background-color: #bfd000; }

      div.clear {clear: both;}
    </style>

    </head>
    <body>
      <a href="${link }"><img src="${resource(dir: 'images/', file: 'logo.png')}" alt="Megapolis" title="" border="0"></a>

      <ul class="menu">
        <li><a href="#">City</a></li>
        <li><a href="#">Profile</a></li>
        <li><a href="#">Economy</a></li>
        <li><a href="#">Build</a></li>
      </ul>

      <div class="clear" style="margin-bottom: 10px;">&nbsp;</div>


        <g:layoutBody />
    </body>
</html>