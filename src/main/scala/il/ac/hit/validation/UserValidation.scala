package il.ac.hit.validation

trait UserValidation extends Function1[User, ValidationResult] {
  //checks if both of the conditions are met
  def and(that: UserValidation): UserValidation = new UserValidation {
    def apply(user: User): ValidationResult = {
      val result1 = UserValidation.this.apply(user)
      val result2 = that.apply(user)

      if (result1.isValid && result2.isValid) {
        new Valid
      } else if (!result1.isValid) {
        result1
      } else {
        result2
      }
    }
  }

  //checks if one of the conditions are met
  def or(that: UserValidation): UserValidation = new UserValidation {
    def apply(user: User) : ValidationResult = {
      val result1 = UserValidation.this.apply(user)
      val result2 = that.apply(user)

      if(!(result1.isValid) && !(result2.isValid))
        new Invalid("Both conditions are not met")
      else
        new Valid()
    }
  }

}

object UserValidation {
  //checks if all the conditions are fulfilled
  def all(conditions: UserValidation*): UserValidation = new UserValidation {
    def apply(user: User): ValidationResult = {
      conditions.foldLeft(new Valid: ValidationResult)
      { (acc, condition) => {
          if (acc.isValid)
            condition.apply(user)
          else acc
        }
      }
    }
  }

  //checks if none of the conditions are fulfilled
  def none(conditions: UserValidation*): UserValidation = new UserValidation {
    def apply(user: User): ValidationResult = {
      conditions.foldLeft(new Valid: ValidationResult)
      { (acc, condition) =>
        if (condition.apply(user).isValid)
          new Invalid("One or more conditions are valid")
        else
          acc
      }
    }
  }

  //function that checks if the email ends with 'il'
  def emailEndsWithIL: UserValidation = new UserValidation {
    def apply(user: User): ValidationResult = {
      if (user.email.toLowerCase.endsWith("il")) {
        new Valid
      } else {
        new Invalid("Email doesn't ends  with 'il'")
      }
    }
  }

  //function that checks if email's length is bigger than 10
  def emailLengthBiggerThan10: UserValidation = new UserValidation{
    def apply(user: User): ValidationResult = {
      if (user.email.length > 10)
        new Valid
      else
        new Invalid("Email's length is not bigger than 10")
    }
  }

  //checks if password length is bigger than 8
  def passwordLengthBiggerThan8: UserValidation = new UserValidation {
    def apply(user: User): ValidationResult = {
      if(user.password.length > 8)
        new Valid
      else
        new Invalid("Password is not bigger than 8")
    }
  }

  //this function checks if the password is built from letters and numbers, or has another special characters in it
  def passwordIncludesLettersNumbersOnly: UserValidation = new UserValidation {
    def apply(user: User): ValidationResult ={
      val pattern = "^[a-zA-Z0-9]+$".r

      user.password match {
        case pattern() => new Valid
        case _ => new Invalid("Password includes characters other than letters and numbers")
      }
    }
  }

  //this function checks if the password contains a dollar sign in it
  def passwordIncludesDollarSign: UserValidation = new UserValidation {
    def apply(user: User): ValidationResult = {
      if(user.password.contains('$'))
        new Valid
      else
        new Invalid("Password doesn't contains a dollar sign in it")
    }
  }

  //this password checks that it is different than the user's username
  def passwordIsDifferentFromUsername : UserValidation = new UserValidation {
    def apply(user: User): ValidationResult = {
      if(user.password != user.username)
        new Valid
      else
        new Invalid("Both password and username are the same")
    }
  }

  //this function checks if the user's age is bigger than 18
  def ageBiggerThan18: UserValidation = new UserValidation {
    def apply(user: User): ValidationResult = {
      if(user.age > 18)
        new Valid
      else
        new Invalid("Age isn't bigger than 18")
    }
  }

  //this function checks if the user's username length is bigger than 8
  def usernameLengthBiggerThan8: UserValidation = new UserValidation {
    def apply(user: User): ValidationResult = {
      if(user.username.length > 8)
        new Valid
      else
        new Invalid("User's username is not bigger than 8")
    }
  }


}


