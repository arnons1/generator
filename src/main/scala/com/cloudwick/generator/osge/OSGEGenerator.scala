package com.cloudwick.generator.osge

import java.util.zip.CRC32

/**
 * Description goes here
 * @author ashrith 
 */
class OSGEGenerator {
  private val crcGenerator = new CRC32()

  def eventGenerate = {
    val person = new Person
    crcGenerator.reset()
    crcGenerator.update(java.util.UUID.randomUUID.toString.getBytes())
    val customerID = crcGenerator.getValue().toString()
    val customer = new Customers(customerID, person.name, person.gender)

    new OSGEEvent(
      customer.custId,
      customer.custName,
      customer.custEmail,
      person.gender,
      person.age,
      customer.custAddress,
      customer.custCountry,
      customer.registerDate,
      customer.custFriendCount,
      customer.custLifeTime,
      customer.custGamesPlayed("bubbles"),
      customer.custGamesPlayed("pictionary"),
      customer.custGamesPlayed("roulette"),
      customer.custGamesPlayed("poker"),
      customer.customerPaidAmount,
      customer.paidSubscriber,
      customer.paidDate
    )
  }
}
