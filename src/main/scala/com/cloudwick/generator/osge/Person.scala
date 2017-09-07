package com.cloudwick.generator.osge

import scala.util.Random
import com.cloudwick.generator.utils.Utils

/**
 * Generates users names
 * @author ashrith
 */
class Person {
  private val random = Random
  private val utils = new Utils

  private val GENDERS = Map(
    "male" -> 59,
    "female" -> 41
  )

  private val lastNames = Array(
    "ABEL", "ANDERSON", "ANDREWS", "ANTHONY", "BAKER", "BROWN", "BURROWS", "CLARK", "CLARKE", "CLARKSON", "DAVIDSON",
    "DAVIES", "DAVIS", "DENT", "EDWARDS", "GARCIA", "GRANT", "HALL", "HARRIS", "HARRISON", "JACKSON", "JEFFRIES",
    "JEFFERSON", "JOHNSON", "JONES", "KIRBY", "KIRK", "LAKE", "LEE", "LEWIS", "MARTIN", "MARTINEZ", "MAJOR", "MILLER",
    "MOORE", "OATES", "PETERS", "PETERSON", "ROBERTSON", "ROBINSON", "RODRIGUEZ", "SMITH", "SMYTHE", "STEVENS",
    "TAYLOR", "THATCHER", "THOMAS", "THOMPSON", "WALKER", "WASHINGTON", "WHITE", "WILLIAMS", "WILSON", "YORKE"
  )

  private val maleFirstNames = Array(
    "ADAM", "ANTHONY", "ARTHUR", "BRIAN", "CHARLES", "CHRISTOPHER", "DANIEL", "DAVID", "DONALD", "EDGAR", "EDWARD",
    "EDWIN", "GEORGE", "HAROLD", "HERBERT", "HUGH", "JAMES", "JASON", "JOHN", "JOSEPH", "KENNETH", "KEVIN", "MARCUS",
    "MARK", "MATTHEW", "MICHAEL", "PAUL", "PHILIP", "RICHARD", "ROBERT", "ROGER", "RONALD", "SIMON", "STEVEN", "TERRY",
    "THOMAS", "WILLIAM"
  )

  private val femaleFirstNames = Array(
    "ALISON", "ANN", "ANNA", "ANNE", "BARBARA", "BETTY", "BERYL", "CAROL", "CHARLOTTE", "CHERYL", "DEBORAH", "DIANA",
    "DONNA", "DOROTHY", "ELIZABETH", "EVE", "FELICITY", "FIONA", "HELEN", "HELENA", "JENNIFER", "JESSICA", "JUDITH",
    "KAREN", "KIMBERLY", "LAURA", "LINDA", "LISA", "LUCY", "MARGARET", "MARIA", "MARY", "MICHELLE", "NANCY", "PATRICIA",
    "POLLY", "ROBYN", "RUTH", "SANDRA", "SARAH", "SHARON", "SUSAN", "TABITHA", "URSULA", "VICTORIA", "WENDY"
  )

  // which age group should appear how many times in the data set
  private val AGE_PROBABILITY = Map(
    "18" -> 15,
    "19" -> 15,
    "20" -> 14,
    "21" -> 14,
    "22" -> 13,
    "23" -> 14,
    "24" -> 12,
    "25" -> 11,
    "26" -> 11,
    "27" -> 12,
    "28" -> 11,
    "29" -> 12,
    "30" -> 10,
    "31" -> 10,
    "32" -> 9,
    "33" -> 8,
    "34" -> 7,
    "35" -> 6,
    "36" -> 5,
    "37" -> 4,
    "38" -> 3,
    "39" -> 2,
    "40" -> 3,
    "41" -> 4,
    "42" -> 3,
    "43" -> 3,
    "44" -> 4,
    "45" -> 3,
    "46" -> 2,
    "47" -> 1,
    "48" -> 1,
    "49" -> 1,
    "50" -> 1,
    "51" -> 1,
    "52" -> 1,
    "53" -> 1,
    "54" -> 1,
    "55" -> 1,
    "56" -> 2,
    "57" -> 2,
    "58" -> 2,
    "59" -> 2,
    "60" -> 1,
    "61" -> 1,
    "62" -> 1,
    "63" -> 1,
    "64" -> 1,
    "65" -> 0,
    "66" -> 0,
    "67" -> 0,
    "68" -> 0,
    "69" -> 1,
    "70" -> 1
  )

  private val lettersArr = ('A' to 'Z').toList

  val gender = utils.pickWeightedKey(GENDERS)

  val name = gender match {
    case "male" => maleName
    case "female" => femaleName
  }

  val age = utils.pickWeightedKey(AGE_PROBABILITY).toInt

  private def initial = lettersArr(random.nextInt(lettersArr.size))

  private def lastName = lastNames(random.nextInt(lastNames.size))

  private def femaleName = s"${femaleFirstNames(random.nextInt(femaleFirstNames.size))} $initial $lastName"

  private def maleName = s"${maleFirstNames(random.nextInt(maleFirstNames.size))} $initial $lastName"
}
