package com.cloudwick.generator.osge

import scala.util.Random
import com.cloudwick.generator.utils.{DateUtils,Utils}

/**
 * Description goes here
 * @author ashrith 
 */
class Address {
  private val utils = new Utils
  private val random = Random

  private val streetNames = Array(
    "Acacia", "Beech", "Birch", "Cedar", "Cherry", "Chestnut", "Elm", "Larch", "Laurel",
    "Linden", "Maple", "Oak", "Pine", "Rose", "Walnut", "Willow", "Adams", "Franklin", "Jackson", "Jefferson",
    "Lincoln", "Madison", "Washington", "Wilson", "Churchill", "Tyndale", "Latimer", "Cranmer", "Highland",
    "Hill", "Park", "Woodland", "Sunset", "Virginia", "1st", "2nd", "4th", "5th", "34th", "42nd"
  )

  private val streetTypes = Array(
    "St", "Ave", "Rd", "Blvd", "Trl", "Rdg", "Pl", "Pkwy", "Ct", "Circle"
  )

  private val line2Types = Array(
    "Apt", "Bsmt", "Bldg", "Dept", "Fl", "Frnt", "Hngr", "Lbby", "Lot", "Lowr", "Ofc", "Ph", "Pier", "Rear", "Rm",
     "Side", "Slip", "Spc", "Stop", "Ste", "Trlr", "Unit", "Uppr"
  )
  private val STATE_PROBABILITY = Map(
   "AK" -> 2,
   "AL" -> 5,
   "AR" -> 2,
   "AZ" -> 12,
   "CA" -> 120,
   "CO" -> 1,
   "CT" -> 12,
   "DC" -> 8,
   "DE" -> 2,
   "FL" -> 40,
   "GA" -> 20,
   "HI" -> 4,
   "IA" -> 13,
   "ID" -> 2,
   "IL" -> 40,
   "IN" -> 13,
   "KS" -> 15,
   "KY" -> 12,
   "LA" -> 20,
   "MA" -> 22,
   "MD" -> 20,
   "ME" -> 2,
   "MI" -> 20,
   "MN" -> 15,
   "MO" -> 3,
   "MS" -> 6,
   "MT" -> 1,
   "NC" -> 20,
   "ND" -> 8,
   "NE" -> 10,
   "NH" -> 2,
   "NJ" -> 20,
   "NM" -> 10,
   "NV" -> 14,
   "NY" -> 50,
   "OH" -> 20,
   "OK" -> 1,
   "OR" -> 1,
   "PA" -> 30,
   "RI" -> 1,
   "SC" -> 1,
   "SD" -> 1,
   "TN" -> 1,
   "TX" -> 80,
   "UT" -> 1,
   "VA" -> 1,
   "VT" -> 1,
   "WA" -> 1,
   "WI" -> 1,
   "WV" -> 1,
   "WY" -> 1
 )
  private val usStates = Array(
    "AK", "AL", "AR", "AZ", "CA", "CO", "CT", "DC", "DE", "FL", "GA", "HI", "IA", "ID", "IL", "IN", "KS", "KY", "LA",
    "MA", "MD", "ME", "MI", "MN", "MO", "MS", "MT", "NC", "ND", "NE", "NH", "NJ", "NM", "NV", "NY", "OH", "OK", "OR",
    "PA", "RI", "SC", "SD", "TN", "TX", "UT", "VA", "VT", "WA", "WI", "WV", "WY"
  )

  /**
   * Generates a address
   * @return a name
   */
  def gen: String = {
    state
  }

  override def toString = state

  private def addressLine1 = s"${random.nextInt(4000)} ${streetNames(random.nextInt(streetNames.size))} ${streetTypes(random.nextInt(streetTypes.size))}"

  private def addressLine2 = s"${line2Types(random.nextInt(line2Types.size))} ${random.nextInt(999)}"

  private def zipCode = "%05d".format(random.nextInt(99999))

  private def state = utils.pickWeightedKey(STATE_PROBABILITY)}
