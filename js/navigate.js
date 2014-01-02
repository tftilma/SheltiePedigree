
function naarHomePage()
{
  window.location.href = "../index.html";
}

function navigate()
{
  // display a New Line (BReak) and a Horizontal Line(Row)
  document.write("<br/>");

  // We do not use a FORM, because the action must directly be used using OnClick
  document.write("<input class='back' type='submit' name='Back' value='Back' onclick='history.go(-1)'></input>");

  document.write("&nbsp;"); // Add some space
  document.write("<input class='home' type='submit' name='HomePage' value='Home' onclick='naarHomePage()'></input>");
}

function w(msg)
{
  document.write(msg);
}

function bs(cond)
{
  if (cond)
  {
    document.write('<b>');
  }
}

function be(cond)
{
  if (cond)
  {
    document.write('</b>');
  }
}

function bm(cond, msg)
{
  bs(cond);
  w(msg);
  be(cond);
}

function abc()
{
  document.write('<br/>');
  document.write('<a href="empty.html">No prefix or affix</a>&nbsp;');
  document.write('<a href="A.html">A</a>&nbsp;');
  document.write('<a href="B.html">B</a>&nbsp;');
  document.write('<a href="C.html">C</a>&nbsp;');
  document.write('<a href="D.html">D</a>&nbsp;');
  document.write('<a href="E.html">E</a>&nbsp;');
  document.write('<a href="F.html">F</a>&nbsp;');
  document.write('<a href="G.html">G</a>&nbsp;');
  document.write('<a href="H.html">H</a>&nbsp;');
  document.write('<a href="I.html">I</a>&nbsp;');
  document.write('<a href="J.html">J</a>&nbsp;');
  document.write('<a href="K.html">K</a>&nbsp;');
  document.write('<a href="L.html">L</a>&nbsp;');
  document.write('<a href="M.html">M</a>&nbsp;');
  document.write('<a href="N.html">N</a>&nbsp;');
  document.write('<a href="O.html">O</a>&nbsp;');
  document.write('<a href="P.html">P</a>&nbsp;');
  document.write('<a href="Q.html">Q</a>&nbsp;');
  document.write('<a href="R.html">R</a>&nbsp;');
  document.write('<a href="S.html">S</a>&nbsp;');
  document.write('<a href="T.html">T</a>&nbsp;');
  document.write('<a href="U.html">U</a>&nbsp;');
  document.write('<a href="V.html">V</a>&nbsp;');
  document.write('<a href="W.html">W</a>&nbsp;');
  document.write('<a href="X.html">X</a>&nbsp;');
  document.write('<a href="Y.html">Y</a>&nbsp;');
  document.write('<a href="Z.html">Z</a>&nbsp;');
  document.write('<br/><br/>');
}

function col(colorId)
{
  var fullColor="";
  switch (colorId)
  {
    case 'sw':   fullColor='Sable and White'; break;
    case 'tri':  fullColor='Tricolour'; break;
    case 'bm':   fullColor='Blue Merle'; break;
    case 'bw':   fullColor='Black and White'; break;
    case 'bb':   fullColor='Bi Blue'; break;
    case 'dm':   fullColor='Double Merle'; break;
    case 'ws':   fullColor='Wheaten Sable'; break;
    case 'sm':   fullColor='Sable Merle'; break;
    case 'bt':   fullColor='Black and Tan'; break;
    case 'chw':  fullColor='Color Headed White'; break;
    case 'mb':   fullColor='Maltese Blue'; break;
  }
  return fullColor;
}

function gender2str(genderId)
{
  if (genderId == 1)
  {
    return "dog";
  }
  else if (gender == 2)
  {
    return "bitch";
  }
  else 
  {
    return "unknown";
  }
}
 