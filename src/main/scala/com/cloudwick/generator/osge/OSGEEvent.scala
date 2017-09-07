package com.cloudwick.generator.osge

/**
 * Description goes here
 * @author ashrith 
 */
case class OSGEEvent (cID: String, cName: String, cEmail: String, cGender: String, cAge: Int, cAddress: String,
                      cCountry: String, cRegisterDate: String, cFriendCount: Int, cLifeTime: Int, bubblesGamePlayed: Int,
                      pictionaryGamePlayed: Int, rouletteGamePlayed: Int, pokerGamePlayed: Int, cRevenue: Int, cRevSource: String,
                      paidSubscriber: String, paidDate: String, playStats : (Int, Int, Int), custPOS : (String, String))
