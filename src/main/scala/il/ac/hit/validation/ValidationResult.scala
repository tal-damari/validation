package il.ac.hit.validation

trait ValidationResult {
  // two abstract methods
  def isValid : Boolean
  def getReason : Option[String]
}
