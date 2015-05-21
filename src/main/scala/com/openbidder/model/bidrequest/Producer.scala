package com.openbidder.model.bidrequest

import play.api.libs.json.Json

case class Producer(id: Option[String], name: Option[String], cat: Option[String], domain: Option[String],
                    ext: Option[Ext])

object Producer {
	implicit val producerRead = Json.reads[Producer] 
	implicit val producerWrite = Json.writes[Producer] 	
} 