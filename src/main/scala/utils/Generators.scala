package utils

import com.nicta.rng.Rng
import com.nicta.rng.Rng._
import com.openbidder.model.bidrequest.device._
import com.openbidder.model.common.Win
import com.openbidder.model.bidrequest._

object Generators {

  /**
   * The random generator for Win is constructed from generators for its parts.
   * @param idg an id random generator, by default is used identifierstring(32)
   * @param pg a price random generator, by default is used positivefloat
   * @param currencies a list of currencies
   * @return a random generated Win
   */
  def randomWin(idg: Rng[String] = identifierstring(32),
                pg: Rng[Float] = positivefloat,
                currencies: scalaz.NonEmptyList[String]): Rng[Win] =
    for {
      aucId <- idg
      aucBidId <- idg.option
      aucImpId <- idg
      aucSeatId <- idg
      aucAdId <- idg.option
      aucPrice <- pg
      aucCur <- oneofL(currencies).option
    } yield Win(auctionId = aucId, auctionBidId = aucBidId, auctionImpId = aucImpId, auctionSeatId = aucSeatId,
      auctionAdId = aucAdId, auctionPrice = aucPrice, auctionCurrency = aucCur)

  def randomApp: Rng[App] = for {
    id <- identifierstring(32).option
    name <- Rng.string(7).option
    bundle <- Rng.string(7).option
    domain <- Rng.string(7).option
    storeurl <- Rng.string(7).option
    cat <- Rng.string(7).list(15).option
    sectioncat <- Rng.string(7).list(15).option
    pagecat <- Rng.string(7).list(15).option
    ver <- Rng.string(7).option
    privacypolicy <- Rng.int.option
    paid <- Rng.int.option
    publisher <- randomPublisher.option
    content <- randomContent.option
    keywords <- Rng.string(7).option


  } yield App(id, name, bundle, domain, storeurl, cat, sectioncat, pagecat, ver, privacypolicy, paid, publisher,
      content, keywords, None)

  def randomAuctionType: Rng[AuctionType] = for {
    id <- Rng.oneof(0, 1, 2, 3).option
  } yield AuctionType(id)

  def randomBanner: Rng[Banner] = for {
    w <- Rng.int.option
    h <- Rng.int.option
    wmax <- Rng.int.option
    hmax <- Rng.int.option
    wmin <- Rng.int.option
    hmin <- Rng.int.option
    id <- identifierstring(32).option
    btype <- Rng.int.list(15).option
    battr <- Rng.int.list(15).option
    pos <- Rng.int.option
    mimes <- Rng.string(7).list(15).option
    topframe <- Rng.int.option
    expdir <- Rng.int.list(15).option
    api <- Rng.int.list(15).option


  } yield Banner(w, h, wmax, hmax, wmin, hmin, id, btype, battr, pos, mimes, topframe, expdir, api, None)

  def randomBidRequest: Rng[BidRequest] = for {
    id <- identifierstring(32)
    imp <- randomImp.list1(15)
    site <- randomSite.option
    app <- randomApp
    device <- randomDevice
    user <- randomUser
    test <- Rng.oneof(0, 1)
    at <- Rng.oneof(0, 1)
    tmax <- Rng.int.option
    wseat <- Rng.string(7).list(15).option
    allimps <- Rng.oneof(0, 1)
    cur <- Rng.string(7).list(15).option
    bcat <- Rng.string(7).list(15).option
    badv <- Rng.string(7).list(15).option
    regs <- randomRegs.option


  } yield BidRequest(id, imp, site, app, device, user, test, at, tmax, wseat, allimps, cur, bcat, badv, regs, None)

  def randomContent: Rng[Content] = for {
    id <- identifierstring(32).option
    episode <- Rng.int.option
    title <- Rng.string(7).option
    series <- Rng.string(7).option
    season <- Rng.string(7).option
    producer <- randomProducer.option
    url <- Rng.string(7).option
    cat <- Rng.string(7).list(15).option
    videoquality <- Rng.int.option
    context <- Rng.int.option
    contentrating <- Rng.string(7).option
    userrating <- Rng.string(7).option
    qagmediarating <- Rng.int.option
    keywords <- Rng.string(7).option
    livestream <- Rng.int.option
    sourcerelationship <- Rng.int.option
    len <- Rng.int.option
    language <- Rng.string(7).option
    embeddable <- Rng.int.option

  } yield Content(id, episode, title, series, season, producer, url, cat, videoquality, context, contentrating,
      userrating, qagmediarating, keywords, livestream, sourcerelationship, len, language, embeddable, None)

  def randomData: Rng[Data] = for {
    id <- identifierstring(32).option
    name <- Rng.string(7).option
    segment <- randomSegment.list(15).option

  } yield Data(id, name, segment, None)

  def randomDeal: Rng[Deal] = for {
    id <- identifierstring(32)
    bidfloor <- Rng.float
    bidfloorcur <- Rng.oneof("USD", "EUR")
    at <- randomAuctionType.option
    wseat <- Rng.string(7).list(15).option
    wadomain <- Rng.string(7).list(15).option

  } yield Deal(id, bidfloor, bidfloorcur, at, wseat, wadomain, None)

  def randomDevice: Rng[Device] = for {
    ua <- Rng.string(7).option
    geo <- randomGeo.option
    dnt <- Rng.int.option
    lmt <- Rng.int.option
    ip <- Rng.string(7).option
    ipv6 <- Rng.string(7).option
    devicetype <- Rng.int.option
    make <- Rng.string(7).option
    model <- Rng.string(7).option
    os <- Rng.string(7).option
    osv <- Rng.string(7).option
    hvm <- Rng.string(7).option
    h <- Rng.int.option
    w <- Rng.int.option
    ppi <- Rng.int.option
    pxratio <- Rng.float.option
    js <- Rng.int.option
    flashver <- Rng.string(7).option
    language <- Rng.string(7).option
    carrier <- Rng.string(7).option
    connectiontype <- Rng.int.option
    ifa <- Rng.string(7).option
    didsha1 <- Rng.string(7).option
    didmd5 <- Rng.string(7).option
    dpidsha1 <- Rng.string(7).option
    dpidmd5 <- Rng.string(7).option
    macsha1 <- Rng.string(7).option
    macmd5 <- Rng.string(7).option
    ext <- randomExt.option

  } yield Device(ua, geo, dnt, lmt, Some(IP(ip, ipv6)), Some(DeviceInfo(devicetype, make, model)), Some(OS(os, osv)),
      hvm, Some(Size(h, w)), ppi, pxratio, js, flashver,
      language, carrier, connectiontype, ifa, Some(DID(didsha1, didmd5)), Some(DPID(dpidsha1, dpidmd5)),
      Some(MAC(macsha1, macmd5)), ext)

  def randomExt: Rng[Ext] = for {
    sessiondepth <- Rng.int.option
  } yield Ext(sessiondepth)

  def randomGeo: Rng[Geo] = for {
    lat <- Rng.float.option
    lon <- Rng.float.option
    geoType <- Rng.int.option
    country <- Rng.string(7).option
    region <- Rng.string(7).option
    regionfips <- Rng.string(7).option
    metro <- Rng.string(7).option
    city <- Rng.string(7).option
    zip <- Rng.string(7).option
    utcoffset <- Rng.int.option
  } yield Geo(lat, lon, geoType, country, region, regionfips, metro, city, zip, utcoffset, None)

  def randomImp: Rng[Imp] = for {
    id <- identifierstring(32)
    banner <- randomBanner.option
    video <- randomVideo.option
    native <- randomNative.option
    displaymanager <- Rng.string(7).option
    displaymanagerver <- Rng.string(7).option
    instl <- Rng.oneof(0)
    tagid <- identifierstring(32).option
    bidfloor <- Rng.oneof(0)
    bidfloorcur <- Rng.oneof("USD", "EUR")
    secure <- Rng.int.option
    iframebuster <- Rng.string(7).list(15).option
    pmp <- randomPmp.option

  } yield Imp(id, banner, video, native,
      displaymanager, displaymanagerver, instl,
      tagid, bidfloor, bidfloorcur,
      secure, iframebuster, pmp,
      None)

  def randomNative: Rng[Native] = for {
    request <- Rng.string(7)
    ver <- Rng.string(7).option
    api <- Rng.int.list(15).option
    battr <- Rng.int.list(15).option

  } yield Native(request, ver, api, battr, None)

  def randomPmp: Rng[Pmp] = for {
    private_auction <- Rng.int.option
    deals <- randomDeal.list(10).option

  } yield Pmp(private_auction, deals, None)

  def randomProducer: Rng[Producer] = for {
    id <- identifierstring(32).option
    name <- Rng.string(7).option
    cat <- Rng.string(7).option
    domain <- Rng.string(7).option

  } yield Producer(id, name, cat, domain,
      None)

  def randomPublisher: Rng[Publisher] = for {
    id <- identifierstring(32).option
    name <- Rng.string(7).option
    cat <- Rng.string(7).option
    domain <- Rng.string(7).option

  } yield Publisher(id, name, cat, domain,
      None)

  def randomRegs: Rng[Regs] = for {
    coppa <- Rng.int.option

  } yield Regs(coppa, None)

  def randomSegment = for {
    id <- identifierstring(32).option
    name <- Rng.string(7).option
    value <- Rng.string(7).option

  } yield Segment(id, name, value, None)

  def randomSite: Rng[Site] = for {
    id <- identifierstring(32).option
    name <- Rng.string(7).option
    domain <- Rng.string(7).option
    cat <- Rng.string(7).list(15).option
    sectioncat <- Rng.string(7).list(15).option
    pagecat <- Rng.string(7).list(15).option
    page <- Rng.string(7).option
    ref <- Rng.string(7).option
    search <- Rng.string(7).option
    mobile <- Rng.int.option
    privacypolicy <- Rng.int.option
    publisher <- randomPublisher.option
    content <- randomContent.option
    keywords <- Rng.string(7).option

  } yield Site(id, name, domain, cat,
      sectioncat, pagecat, page,
      ref, search, mobile,
      privacypolicy, publisher, content,
      keywords, None)

  def randomUser: Rng[User] = for {
    id <- identifierstring(32).option
    buyerid <- identifierstring(32).option
    yop <- Rng.int.option
    gender <- Rng.string(7).option
    keywords <- Rng.string(7).option
    customdata <- Rng.string(7).option
    geo <- randomGeo.option
    data <- randomData.list(15).option

  } yield User(id, buyerid, yop, gender,
      keywords, customdata, geo, data,
      None)

  def randomVideo: Rng[Video] = for {
    mimes <- Rng.string(10).list(4)
    minduration <- Rng.int.option
    maxduration <- Rng.int.option
    protocols <- Rng.int.list(15).option
    w <- Rng.int.option
    h <- Rng.int.option
    startdelay <- Rng.int.option
    linearity <- Rng.int.option
    sequence <- Rng.int.option
    battr <- Rng.int.list(15).option
    maxextended <- Rng.int.option
    minbitrate <- Rng.int.option
    maxbitrate <- Rng.int.option
    boxingallowed <- Rng.oneof(1, 0)
    playbackmethod <- Rng.int.list(15).option
    delivery <- Rng.int.list(15).option
    pos <- Rng.int.option
    companionad <- randomBanner.list(15).option
    api <- Rng.int.list(15).option
    companiontype <- Rng.int.list(15).option
  } yield Video(mimes, minduration, maxduration,
      protocols, w, h,
      startdelay, linearity, sequence,
      battr, maxextended, minbitrate,
      maxbitrate, boxingallowed,
      playbackmethod, delivery,
      pos, companionad, api,
      companiontype, None)
}
