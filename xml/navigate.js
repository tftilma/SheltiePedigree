
function naarHomePage()
{
  window.location.href = "../../index.html";
}

function navigate()
{
  // display a New Line (BReak) and a Horizontal Line(Row)
  document.write("<BR> <HR>");
  
  // We do not use a FORM, because the action must directly be used using OnClick
  document.write("<INPUT TYPE='SUBMIT' NAME='Terug' VALUE='Terug' OnClick='history.go(-1)'></INPUT>");

  framed = window.parent.framed;
  if (framed != 1)
  {
    document.write("  &nbsp;"); // Add some space
    document.write("  <INPUT TYPE='SUBMIT' NAME='HomePage' VALUE='Homepage' OnClick='naarHomePage()'></INPUT>");
  }
}
