package com.cloudwick.generator.osge

import com.cloudwick.generator.utils.{DateUtils, Utils}
import scala.util.Random
import java.text.SimpleDateFormat
import java.util.Calendar

/**
 * Generates customer records with companion object
 * @author ashrith
 */

object Customers {
  // which country should occur at what probability
  private val COUNTRY_PROBABILITY = Map(
    "USA"      -> 70,
    "UK"       -> 20,
    "CANADA"   -> 7,
    "MEXICO"   -> 3
  )

  // game most played by females
  val GAMES_FEMALE_PROBABILITY = Map(
    "bubbles"       -> 70,
    "pictionary" -> 30,
    "roulette"   -> 15,
    "poker"     -> 5
  )

  // game most played by males
  val GAMES_MALE_PROBABILITY = Map(
    "bubbles"     -> 2,
    "roulette"   -> 40,
    "pictionary" -> 8,
    "poker"       -> 50  )
}

class Customers(cId: String, cName: String, cGender: String, cPlatform: String, cOs: String) {
  private val utils = new Utils
  private val dateUtils = new DateUtils
  private val random = Random
  private val formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")

  /*
   * Accessor(s)
   */
  val custId = cId
  val custName = cName
  val custEmail = genEmail
  val custPlatform = cPlatform
  val custOs = cOs
  val registerDate = dateUtils.genDate("2010-01-01 12:10:00", formatter.format(Calendar.getInstance().getTimeInMillis))
  val custCountry = utils.pickWeightedKey(Customers.COUNTRY_PROBABILITY)
  val custAddress = custCountry match {
    case "USA" => new Address().toString
    case _ => "N/A"
  }
  // users who pay will have a average friends count of > 10
  private val paidCustomerFriendCount = 10
  // maximum friends each user can have
  private val maxFriendCountRange = 500
  // 40 % of customers are paid
  private val paidCustomerPercent = 0.4
  // set 60% of the customers life time to < 10 days and others to 10-100 days
  val custLifeTime = if (random.nextFloat() <  0.6) {
                          random.nextInt(10)
                         } else {
                          utils.randInt(10, 100)
                         }
  // 30% of the users don't have any friends at all
  val custFriendCount = if (random.nextFloat() < 0.3) {
                          0
                        } else {
                          // 40% of users will have fried count > 5 and other will be friend < 5
                          if (random.nextFloat() < 0.4) {
                            utils.randInt(paidCustomerFriendCount, maxFriendCountRange)
                          } else {
                            random.nextInt(paidCustomerFriendCount)
                          }
                        }
  // users who have friend count > paidCustomerFriendCount and total life time in the site > 20 are paid subcribers
  val paidSubscriber =  if (custFriendCount > paidCustomerFriendCount && custLifeTime > 20) {
                          if (random.nextFloat() < paidCustomerPercent) {
                            "yes"
                          } else {
                            "no"
                          }
                        } else {
                          "no"
                        }

  val customerPaidAmount =  if (paidSubscriber == "yes") {
                              if (random.nextFloat() < 0.8) {
                                utils.randInt(5, 30)
                              } else { // 30 - 99
                                utils.randInt(30, 99)
                              }
                            } else {
                              0
                            }
  val customerRevSource =  if (paidSubscriber == "yes") {
                              if (random.nextFloat() < 0.8) {
                                "Advertising"
                              } else { // 30 - 99
                                "Credit"
                              }
                            } else {
                              ""
                            }
  val paidDate =  if (customerPaidAmount == 0) {
                    ""
                  } else {
                    // generate a date between users registration date and time now
                    dateUtils.genDate(registerDate, formatter.format(Calendar.getInstance().getTimeInMillis))
                  }
  // games played by user based on gender
  val custGamesPlayed = gamesPlayed(custLifeTime, cGender)
  val playTime = playTimeGen(custLifeTime, cGender)
  val numWins = numWinGen(custGamesPlayed, cGender)
  val numLosses = numLossGen(custGamesPlayed, cGender)
  override def toString = custId + " " + custEmail + " " + registerDate + " " + custCountry + " " + custAddress +
                          " " + custLifeTime + " " + custFriendCount + " " + paidSubscriber + " " + customerPaidAmount +
                          " " + paidDate + " " + custGamesPlayed.toString

  private def genEmail = {
    val domains = Array("yahoo.com", "gmail.com", "privacy.net", "webmail.com", "msn.com",
      "hotmail.com", "example.com", "privacy.net")
    s"${domains(random.nextInt(domains.size))}"
  }

  private def gamesPlayed(customerLifeTime: Int, customerGender: String) = {
    val counter = collection.mutable.Map(
      "bubbles" -> 0,
      "pictionary" -> 0,
      "poker" -> 0,
      "roulette" -> 0
    )
    val gamesProbMap =  if (customerGender == "female") {
                          Customers.GAMES_FEMALE_PROBABILITY
                        } else {
                          Customers.GAMES_MALE_PROBABILITY
                        }
    1 to customerLifeTime foreach { _ =>
      utils.pickWeightedKey(gamesProbMap) match {
        case "bubbles" => counter("bubbles") += 1
        case "pictionary" => counter("pictionary") += 1
        case "poker" => counter("poker") += 1
        case "roulette" => counter("roulette") += 1
      }
    }
    counter
  }

  private def playTimeGen(customerLifeTime: Int, customerGender: String) = {
    val counter = collection.mutable.Map(
      "bubbles" -> 0,
      "pictionary" -> 0,
      "poker" -> 0,
      "roulette" -> 0
    )
    val gamesProbMap =  if (customerGender == "female") {
                          Customers.GAMES_FEMALE_PROBABILITY
                        } else {
                          Customers.GAMES_MALE_PROBABILITY
                        }
    1 to customerLifeTime foreach { _ =>
      utils.pickWeightedKey(gamesProbMap) match {
        case "bubbles" => counter("bubbles") += 15
        case "pictionary" => counter("pictionary") += 14
        case "poker" => counter("poker") += 22
        case "roulette" => counter("roulette") += 10
      }
    }
    counter("poker") + counter("bubbles") + counter("pictionary") + counter("roulette")
  }

  private def numWinGen(gamesPlayed: collection.mutable.Map[String,Int], customerGender: String)  = {
      val wins =  if (customerGender == "female") {
                          gamesPlayed("bubbles") + ((gamesPlayed("pictionary")*0.8).toInt) + (gamesPlayed("poker")/3) + (gamesPlayed("roulette")/2)
                        } else {
                          gamesPlayed("bubbles") + (gamesPlayed("pictionary")/2) + (gamesPlayed("poker")/2) + (gamesPlayed("roulette")/2)
      }
      wins
  }
    private def numLossGen(gamesPlayed: collection.mutable.Map[String,Int], customerGender: String)  = {
      val loss =  if (customerGender == "female") {
                          ( gamesPlayed("pictionary") - (gamesPlayed("pictionary")*0.2).toInt 
                           + gamesPlayed("poker") - (gamesPlayed("poker")/3) + gamesPlayed("roulette") - (gamesPlayed("roulette")/2) )
                        } else {
                          ( gamesPlayed("pictionary") - (gamesPlayed("pictionary")/2)
                           + gamesPlayed("poker") - (gamesPlayed("poker")/2) + gamesPlayed("roulette") - (gamesPlayed("roulette")/2) )
      }
      loss
  }
}
