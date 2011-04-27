<html>
    <head>
        <title><g:layoutTitle default="Grails" /></title>
         <link rel="stylesheet" href="${resource(dir:'css',file:'megapolis.css')}" />
        <link rel="shortcut icon" href="${resource(dir:'images',file:'favicon.ico')}" type="image/x-icon" />
        <g:javascript src="jquery-1.5.2.min.js" />
        <g:javascript src="jquery-ui-1.8.12.custom.min.js" />
        <g:javascript library="application" />
        <g:javascript library="prototype" />
      <g:layoutHead />

    </head>
    <body>
      %{--<a href="${link }"><img src="${resource(dir: 'images/', file: 'logo.png')}" alt="Megapolis" title="" border="0"></a>--}%




        <g:layoutBody />
    </body>
</html>