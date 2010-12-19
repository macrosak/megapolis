<!--
  To change this template, choose Tools | Templates
  and open the template in the editor.
-->

<%@ page contentType="text/html;charset=UTF-8" %>

<html>
  <head>
    <meta name="layout" content="main"/>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Sample title</title>

  </head>
  <body>

  <div style="width:760px; height: 500px; border:1px solid black; ">
    <img id="klikatko" src="${resource( dir:'images', file: 'mapcontrols.png')}" usemap="#_klikatko" border="0" width="59" height="59" alt="" />
    <map id="_klikatko" name="klikatko">
    <area shape="rect" coords="19,34,37,52" href="http://www.image-maps.com/" alt="" title=""    />
    <area shape="rect" coords="35,18,53,36" href="http://www.image-maps.com/" alt="" title=""    />
    <area shape="rect" coords="19,1,37,19" href="http://www.image-maps.com/" alt="" title=""    />
    <area shape="rect" coords="3,18,21,36" href="http://www.image-maps.com/" alt="" title=""    />
    <area shape="rect" coords="19,18,37,35" href="http://www.image-maps.com/" alt="" title=""    />
    </map>
  </div>

  </body>
</html>
