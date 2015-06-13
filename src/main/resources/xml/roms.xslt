<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet 
	version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output 
    method="html"  
    encoding="iso-8859-1"
    omit-xml-declaration="yes"
    doctype-public="-//W3C//DTD XHTML 1.0 Transitional//EN"
    doctype-system="http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"
    standalone="no"
    indent="yes"
    />

<xsl:template match="animals">
var id="?";
var name="?";
var born = "";
var position = "";
var chyear = "";
var gender = "";
var color = "";
var romType = "";
var romTitle = "";
var line="";
var fam="";
romType   = "<xsl:value-of select='romType'/>";
if (romType == "ROM")
{romTitle = "American ROMs";}
else if (romType == "ROMC")
{romTitle = "Canadian ROMCs";}
else if (romType == "ROMA")
{romTitle = "Australian ROMAs";}
w('<h2>'+romTitle+'</h2>');
w('<h3>Register Of Merit</h3>');
w('<table>');
w('<tr>');
w('<th>Nr</th>');
w('<th>name</th>');
w('<th>Gender</th>');
w('<th>Date of Birth</th>');
w('<th>Line</th>');
w('<th>Fam</th>');
w('<th>Colour</th>');
w('</tr>');
<xsl:apply-templates select="animal"/>
w('</table>');
</xsl:template>

<xsl:template match="animal">
position="<xsl:value-of select='position'/>";
id="<xsl:value-of select='animalid'/>";
name="<xsl:value-of select='animaltotalname'/>";
born="<xsl:value-of select='born'/>";
chyear="<xsl:value-of select='chyear'/>";
gender="<xsl:value-of select='gender'/>";
color="<xsl:value-of select='color'/>";
line="<xsl:value-of select='line'/>";
fam="<xsl:value-of select='fam'/>";
w('<tr>');
w('<td>' + position +'</td>');
w('<td>');
w('<a href="'+id +'.html">'+name+' </a>');
w('</td>');
w('<td>' + gender+ '</td>');
w('<td>'+born+'</td>');
w('<td>'+line+'</td>');
w('<td>'+fam+'</td>');
w('<td>'+col(color)+'</td>');
w('</tr>');
</xsl:template>

    
<xsl:template match="/">   
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"/>
    <meta name="description"
        content="Homepage of Portma Zathe Shetland Sheepdogs and Shetland Ponys"/>
    <meta name="keywords"
        content="Portma Zathe, Sheltie, Shetland Sheepdog, pedigree"/>
    <title>Register Of Merit</title>
    <script language="JavaScript" type="text/javascript" src="../js/navigate.js">
        var leeg=1;
    </script>
    <link rel="stylesheet" type="text/css" href="../js/stylesheet.css" media="all">
    </link>
    <link rel="stylesheet" type="text/css" href="../css/rom.css" media="all">
    </link>
</head>
<body>
<div class="edge1">
<div class="edge2">
<center>
<script language="JavaScript" type="text/javascript">
    var nyAnimals = "1";
  <xsl:apply-templates select="animals"/>
</script>
<br/>
<img border="no" src="../img/_longbar.jpg"/>
<h3>Alphabet of Kennels</h3>
<script language="JavaScript" type="text/javascript">
  abc();
</script>
<br/>
</center>

</div>
</div>
<script language="JavaScript" type="text/javascript">
navigate();
</script>
</body>
</html>
</xsl:template>
</xsl:stylesheet>
