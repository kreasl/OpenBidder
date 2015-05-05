package com.openbidder.model.bidrequest

case class Banner(w: Option[Int], h: Option[Int], wmax: Option[Int], hmax: Option[Int], wmin: Option[Int],
                  hmin: Option[Int], id: Option[String], btype: Option[Seq[Int]],
                  battr: Option[Seq[Int]], pos: Option[Int], mimes: Option[Seq[String]],
                  topframe: Option[Int], expdir: Option[Seq[Int]], api: Option[Seq[Int]],
                  ext: Option[Any])