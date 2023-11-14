package br.unb.cic.sootup

import br.unb.cic.JSVFA

class JSVFATest(className: String, 
                mainMethodName: String, 
                mainMethodReturnType: String, 
                pathTest: String) extends JSVFA {

  override def getClassName: String = className

  override def getMainMethodName: String = mainMethodName

  override def getMainMethodReturnType: String = mainMethodReturnType

  override def getPathTest: String = pathTest
  
}
