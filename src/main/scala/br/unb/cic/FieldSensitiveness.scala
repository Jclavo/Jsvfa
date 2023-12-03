package br.unb.cic

trait FieldSensitiveness {
  def isFieldSensitiveAnalysis(): Boolean
}

trait FieldSensitive extends FieldSensitiveness {
  override def isFieldSensitiveAnalysis(): Boolean = true
}

trait FieldInsensitive extends FieldSensitiveness {
  override def isFieldSensitiveAnalysis(): Boolean = false
}
