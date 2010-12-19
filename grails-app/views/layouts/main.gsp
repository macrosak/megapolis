<html>
    <head>
        <title><g:layoutTitle default="Grails" /></title>
        %{-- <link rel="stylesheet" href="${resource(dir:'css',file:'main.css')}" /> --}%
        <link rel="shortcut icon" href="${resource(dir:'images',file:'favicon.ico')}" type="image/x-icon" />

        <g:javascript library="application" />
        <g:javascript library="prototype" />
      <g:layoutHead />

    <style type="text/css">
      body { width: 760px; }

      body, p, table, div {font-family:verdana}

      ul.menu { margin:0; padding:0; list-style-type: none; width:100%; font-family: verdana; background-color: #fdca00; }
      .menu li { margin:0; padding:0; float: left; text-align:center; margin-right:10px ;}

      .menu a { color: black; background-color: #fdca00; display: block; padding:5px; width:100%; /* border: 1px solid #d29e00;*/ text-decoration: none;}
      .menu a:hover { background-color: #bfd000; }

      div.clear { clear: both; }

      .income {color: green;}
    </style>

    </head>
    <body>
      <a href="${link }"><img src="${resource(dir: 'images/', file: 'logo.png')}" alt="Megapolis" title="" border="0"></a>

      <ul class="menu">
        <li><g:link action="show" >City</g:link></li>
        <li><g:link action="profile" >Profile</g:link></li>
        %{--<li><g:link action="economy" >Economy</g:link></li>--}%
        <li><g:link action="buyBuild" >Build</g:link></li>
      </ul>

      <div class="clear" style="margin-bottom: 10px;"></div>
      
        <g:layoutBody />
    </body>
</html>