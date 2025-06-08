package artyA7100TVgaTest

import spinal.core._
import spinal.lib._
import spinal.lib.eda.altera.QuartusFlow
import spinal.lib.eda.altera.QuartusProject
import spinal.lib.graphic.Rgb
import spinal.lib.graphic.RgbConfig
import libcheesevoyage._
import libcheesevoyage.general._
import libcheesevoyage.gfx._
import libcheesevoyage.hwdev._
import scala.collection.mutable.ArrayBuffer
import libcheesevoyage.gfx.Gpu2dTestGfx

object MyTopLevelForYosysIo {
  //def physRgbConfig = RgbConfig(rWidth=4, gWidth=4, bWidth=4)
  def rgbConfig = RgbConfig(rWidth=6, gWidth=6, bWidth=6)
  //def physRgbConfig = LcvVideoDithererIo(rgbConfig=rgbConfig).outRgbConfig
  //def physRgbConfig = RgbConfig(rWidth=4, gWidth=4, bWidth=4)
  def physRgbConfig = LcvVideoDithererIo.outRgbConfig(rgbConfig=rgbConfig)
}

//case class LedRgbIo(
//  index: Int
//) extends Bundle {
//}

case class MyTopLevelForYosysIo(
  //rgbConfig: RgbConfig
) extends Bundle {
  //val clk50Mhz = in Bool()
  //val rst = in Bool()
  //val inpEn = in Bool()
  //val inpEn = in Bool()
  //val outpEn = out Bool()
  //val outpDbgTrigger0 = out Bool()
  //val outpDbgTrigger1 = out Bool()
  //val outpDbgTrigger2 = out Bool()

  //val outpDbgArrCnt0 = out Bool()
  //val outpDbgArrCnt1 = out Bool()
  //val outpDbgArrCnt2 = out Bool()
  //val outpDbgArrCnt3 = out Bool()
  //val outpDbgArrCnt4 = out Bool()
  //val outpDbgArrCnt5 = out Bool()
  //val outpDbgArrCnt6 = out Bool()

  //val outpDbgPhysColR0 = out Bool()
  //val outpDbgPhysColR1 = out Bool()
  //val outpDbgPhysColR2 = out Bool()
  //val outpDbgPhysColR3 = out Bool()
  //val outpDbgPhysColG0 = out Bool()
  //val outpDbgPhysColG1 = out Bool()
  //val outpDbgPhysColG2 = out Bool()
  //val outpDbgPhysColG3 = out Bool()
  //val outpDbgPhysHsync = out Bool()
  //val outpDbgPhysVsync = out Bool()
  //val outpVga = out(LcvVgaPhys(rgbConfig=rgbConfig))
  //val outpVga = 

  //val snesCtrlClk = out Bool()
  //val snesCtrlLatch = out Bool()
  //val snesCtrlData = in Bool()
  //--------
  val snesCtrl = SnesCtrlIo()
  //--------

  //val inpReadSnesButtons = in Bool()
  //val inpResetReadSnesButtons = in Bool()

  val outpVgaColR = new ArrayBuffer[Bool]()
  val outpVgaColG = new ArrayBuffer[Bool]()
  val outpVgaColB = new ArrayBuffer[Bool]()

  val outpVgaHsync = out Bool()
  val outpVgaVsync = out Bool()

  for (idx <- 0 to MyTopLevelIo.physRgbConfig.rWidth - 1) {
    outpVgaColR += out Bool()
    outpVgaColR.last.setName(f"io_outpVgaColR_$idx")
  }
  for (idx <- 0 to MyTopLevelIo.physRgbConfig.gWidth - 1) {
    outpVgaColG += out Bool()
    outpVgaColG.last.setName(f"io_outpVgaColG_$idx")
  }
  for (idx <- 0 to MyTopLevelIo.physRgbConfig.bWidth - 1) {
    outpVgaColB += out Bool()
    outpVgaColB.last.setName(f"io_outpVgaColB_$idx")
  }

  val outpLedR = new ArrayBuffer[Bool]()
  val outpLedG = new ArrayBuffer[Bool]()
  val outpLedB = new ArrayBuffer[Bool]()
  val outpLedArr = new ArrayBuffer[Bool]()
  for (idx <- 0 to 4 - 1) {
    outpLedR += out Bool()
    outpLedR.last.setName(f"io_led$idx" + "_r")
    outpLedG += out Bool()
    outpLedG.last.setName(f"io_led$idx" + "_g")
    outpLedB += out Bool()
    outpLedB.last.setName(f"io_led$idx" + "_b")
    outpLedArr += out Bool()
    outpLedArr.last.setName(f"io_led$idx")
  }

  //val led0_b = out Bool()
  //val led0_g = out Bool()
  //val led0_r = out Bool()
  //val led1_b = out Bool()
  //val led1_g = out Bool()
  //val led1_r = out Bool()
  //val led2_b = out Bool()
  //val led2_g = out Bool()
  //val led2_r = out Bool()
  //val led3_b = out Bool()
  //val led3_g = out Bool()
  //val led3_r = out Bool()
}

//--------
// BEGIN: code for outputting .edif
case class MyTopLevelForYosys(
  //rgbConfig: RgbConfig=RgbConfig(rWidth=4, gWidth=4, bWidth=4),
) extends Component {
  //--------
  val io = MyTopLevelForYosysIo(
    //rgbConfig=rgbConfig
  )
  //def clkRate = 400.0 MHz
  //def clkRate = 297.0 MHz // 1920x1080@60 (2x pixel clock)
  //def clkRate = 354.75 MHz // 1600x900@60 (3x pixel clock)
  //def clkRate = 236.5 MHz // 1600x900@60 (2x pixel clock)
  //def clkRate = 319.5 MHz // 1440x900@60 (3x pixel clock)
  //def clkRate = 213.0 MHz // 1440x900@60 (2x pixel clock)
  //def clkRate = 300.0 MHz
  //def clkRate = 275.0 MHz
  //def clkRate = 250.0 MHz
  //def clkRate = 225.0 MHz
  //def clkRate = 200.0 MHz
  def clkRate = 150.0 MHz // 640x480@60 (6x pixel clock)
  //def clkRate = 125.0 MHz // 640x480@60 (5x pixel clock)
  //def clkRate = 100.0 MHz // 640x480@60 (4x pixel clock)
  //def clkRate = 50.0 MHz // 640x480@60 (2x pixel clock)
  //val vgaTimingInfo = LcvVgaTimingInfo(
  //  pixelClk=pixelClk,
  //  htiming=LcvVgaTimingHv(
  //    visib=640,
  //    front=16,
  //    sync=96,
  //    back=48
  //  ),
  //  vtiming=LcvVgaTimingHv(
  //    visib=480,
  //    front=10,
  //    sync=2,
  //    back=33
  //  ),
  //)
  val vgaTimingInfo = LcvVgaTimingInfoMap.map("640x480@60")
  //val vgaTimingInfo = LcvVgaTimingInfoMap.map("1440x900@60")
  //val vgaTimingInfo = LcvVgaTimingInfoMap.map("1600x900@60")
  //val vgaTimingInfo = LcvVgaTimingInfoMap.map("1920x1080@60")
  def vivadoDebug = true
  //def gpu2dCfg = DefaultGpu2dConfig(
  //  //rgbConfig=rgbConfig,
  //  rgbConfig=MyTopLevelIo.physRgbConfig,
  //  intnlFbSize2d=ElabVec2[Int](
  //    //x=vgaTimingInfo.fbSize2d.x,
  //    //y=vgaTimingInfo.fbSize2d.y,
  //    x=vgaTimingInfo.fbSize2d.x >> 1,
  //    y=vgaTimingInfo.fbSize2d.y >> 1,
  //    //x=480,
  //    //y=270,
  //  ),
  //  physFbSize2dScalePow=ElabVec2[Int](
  //    x=log2Up(1),
  //    y=log2Up(1),
  //    //x=log2Up(2),
  //    ////y=log2Up(2),
  //    //y=log2Up(2),
  //  ),
  //  bgTileSize2dPow=ElabVec2[Int](
  //    x=log2Up(8),
  //    y=log2Up(8),
  //    //x=log2Up(4),
  //    //y=log2Up(4),
  //    //x=log2Up(2),
  //    //y=log2Up(2),
  //  ),
  //  objTileSize2dPow=ElabVec2[Int](
  //    x=log2Up(8),
  //    y=log2Up(8),
  //    //x=log2Up(4),
  //    //y=log2Up(4),
  //    //x=log2Up(2),
  //    //y=log2Up(2),
  //  ),
  //  //numBgsPow=log2Up(4),
  //  numBgsPow=log2Up(2),
  //  //numObjsPow=log2Up(64),
  //  //numObjsPow=log2Up(32),
  //  //numObjsPow=log2Up(2),
  //  //numObjsPow=log2Up(32),
  //  //numObjsPow=log2Up(16),
  //  //numObjsPow=log2Up(2),
  //  //numObjsPow=log2Up(4),
  //  numObjsPow=log2Up(128),
  //  numObjsAffinePow=log2Up(32),
  //  numBgTiles=Some(256),
  //  //numBgTilesPow=Some(log2Up(2)),
  //  //numBgTilesPow=Some(log2Up(16)),
  //  //numObjTilesPow=None,
  //  //numObjTilesPow=Some(log2Up(8)),
  //  numObjTiles=Some(128),
  //  numObjAffineTiles=Some(128),
  //  //numColsInBgPalPow=log2Up(64),
  //  //numColsInObjPalPow=log2Up(64),
  //  numColsInBgPalPow=log2Up(256),
  //  numColsInObjPalPow=log2Up(256),
  //  //--------
  //  noColorMath=true,
  //  noAffineBgs=true,
  //  noAffineObjs=true,
  //  fancyObjPrio=true,
  //  //--------
  //  //bgTileArrRamStyle="auto",
  //  //objTileArrRamStyle="auto",
  //  //bgEntryArrRamStyle="auto",
  //  ////bgAttrsArrRamStyle="auto",
  //  //objAttrsArrRamStyle="auto",
  //  //bgPalEntryArrRamStyle="auto",
  //  //objPalEntryArrRamStyle="auto",
  //  ////bgLineArrRamStyle="auto",
  //  ////objLineArrRamStyle="auto",
  //  ////combineLineArrRamStyle="auto",
  //  //lineArrRamStyle="auto",
  //  //--------
  //  bgTileArrRamStyle="block",
  //  objTileArrRamStyle="block",
  //  bgEntryArrRamStyle="block",
  //  //bgAttrsArrRamStyle="block",
  //  objAttrsArrRamStyle="block",
  //  bgPalEntryArrRamStyle="block",
  //  objPalEntryArrRamStyle="block",
  //  //bgLineArrRamStyle="block",
  //  //objLineArrRamStyle="block",
  //  //combineLineArrRamStyle="block",
  //  lineArrRamStyle="block",
  //  //--------
  //)
  def gpu2dBgTileSize2dPow=ElabVec2[Int](
    //x=log2Up(8),
    //y=log2Up(8),
    x=log2Up(4),
    y=log2Up(4),
    //x=log2Up(2),
    //y=log2Up(2),
  )
  def gpu2dCfg = DefaultGpu2dConfig(
    rgbConfig=MyTopLevelIo.physRgbConfig,
    intnlFbSize2d=ElabVec2[Int](
      x=vgaTimingInfo.fbSize2d.x,
      y=vgaTimingInfo.fbSize2d.y,
    ),
    //physFbSize2dScalePow=ElabVec2[Int](
    //  x=log2Up(1),
    //  y=log2Up(1),
    //  //x=log2Up(2),
    //  ////y=log2Up(2),
    //  //y=log2Up(2),
    //),
    bgTileSize2dPow=(
      //ElabVec2[Int](
      //  //x=log2Up(8),
      //  //y=log2Up(8),
      //  x=log2Up(4),
      //  y=log2Up(4),
      //  //x=log2Up(2),
      //  //y=log2Up(2),
      //)
      gpu2dBgTileSize2dPow
    ),
    objTileSize2dPow=ElabVec2[Int](
      x=log2Up(8),
      y=log2Up(8),
      //x=log2Up(4),
      //y=log2Up(4),
      //x=log2Up(2),
      //y=log2Up(2),
    ),
    //objTileWidthRshift=0,
    objTileWidthRshift=1,
    objAffineTileSize2dPow=ElabVec2[Int](
      x=log2Up(8),
      y=log2Up(8),
      //x=log2Up(4),
      //y=log2Up(4),
      //x=log2Up(2),
      //y=log2Up(2),
    ),
    //objAffineTileWidthRshift=0,
    objAffineTileWidthRshift=1,
    //numBgsPow=log2Up(4),
    numBgsPow=log2Up(2),
    //numObjsPow=log2Up(64),
    //numObjsPow=log2Up(32),
    //numObjsPow=log2Up(2),
    //numObjsPow=log2Up(32),
    //numObjsPow=log2Up(16),
    //numObjsPow=log2Up(2),
    //numObjsPow=log2Up(4),
    //numObjsPow=log2Up(8),
    numObjsPow=log2Up(16),
    numObjsAffinePow=log2Up(16),
    //numBgTilesPow=Some(log2Up(256)),
    //numBgTilesPow=Some(log2Up(2)),
    numBgTiles=(
      //Some(16)
      //Some(320 * 240)
      // for double buffering
      Some(
        vgaTimingInfo.fbSize2d.x
        * vgaTimingInfo.fbSize2d.y
        * 2
        / (1 << (gpu2dBgTileSize2dPow.x + gpu2dBgTileSize2dPow.y))
      )
    ),
    //numObjTilesPow=None,
    numObjTiles=Some(16),
    numObjAffineTiles=Some(16),
    numColsInBgPalPow=log2Up(64),
    numColsInObjPalPow=log2Up(64),
    noColorMath=true,
    noAffineBgs=true,
    //noAffineObjs=true,
    noAffineObjs=false,
    //fancyObjPrio=false,
    fancyObjPrio=true,
  )
  //--------
  val clkCtrl = new Area {
    //val pllRstCnt = Reg(UInt(2 bits)) init(U"11")
    //val rOutpEn = Reg(UInt(2 bits)) init(U"11")
    //io.outpEn := io.inpEn
    //io.outpEn := rOutpEn === 0
    //switch (rOutpEn) {
    //  is (0x0) {
    //    io.outpEn := 
    //  }
    //  is (0x1) {
    //  }
    //  is (0x2) {
    //  }
    //  is (0x3) {
    //  }
    //}
    //when (io.inpEn) {
    //  when
    //}
    //val tempRst = Bool()
    //tempRst := pllRstCnt =/= 0
    ////when (io.inpEn) {
    //  when (tempRst) {
    //    pllRstCnt := pllRstCnt - 1
    //  }
    ////}
    // Instantiate and drive the PLL
    //val pll = new pll50To100p7Mod
    val pll = new my_core_clk_wiz
    //pll.io.refclk := io.clk50Mhz
    //pll.io.refclk := clockDomain.clock
    pll.io.clk_in1_0 := clockDomain.readClockWire
    //val tempRst = (pllRstCnt =/= 0)
    //pll.io.reset := tempRst

    //val pwrOnRst = new PwrOnRst()
    //pwrOnRst.io.refclk := io.clk50Mhz
    //pll.io.rst := pwrOnRst.io.rst

    // Create a new clock domain named "core"
    val coreClockDomain = ClockDomain.internal(
      name="core",
      config=ClockDomainConfig(
        //resetActiveLevel = HIGH
        resetKind=BOOT,
      ),
      //frequency=FixedFrequency(100.7 MHz),
      //frequency=FixedFrequency(100 MHz),
      frequency=FixedFrequency(clkRate)
      //frequency=FixedFrequency(400 MHz),
      //frequency=FixedFrequency(350 MHz),
      //frequency=FixedFrequency(300 MHz),
      //frequency=FixedFrequency(250 MHz),
      //frequency=FixedFrequency(200 MHz),
      //frequency=FixedFrequency(150 MHz),
      //frequency=FixedFrequency(125 MHz),
      //frequency=FixedFrequency(100 MHz),
    )

    // Drive clock and reset of the `coreClockDomain` previously created
    coreClockDomain.clock := pll.io.clk_out1_0
    //coreClockDomain.reset := ResetCtrl.asyncAssertSyncDeassert(
    //  //input=io.rst || !pll.io.locked,
    //  //input=(!pll.io.locked),
    //  //input=(pllRstCnt =/= 0) || !pll.io.locked,
    //  input=tempRst || !pll.io.locked,
    //  clockDomain=coreClockDomain,
    //)
  }

  // Create a `ClockingArea` which will be under the effect of
  // `clkCtrl.coreClockDomain` 
  //val core = new ClockingArea(clkCtrl.coreClockDomain) 
  val core = new Area {
    //val pixelClk = 25.0 
    ////def pixelClk = 25.175
    //def ctrlFifoDepth = 100
    //val ctrlFifoDepth = 64
    val ctrlFifoDepth = 10
    //val dbgTrigger = Reg(Bool()) init(False) addAttribute("keep")
    //dbgTrigger := True
    //io.outpDbgTrigger0 := dbgTrigger
    //--------
    val vgaCtrl = LcvVgaCtrl(
      clkRate=clkRate,
      rgbConfig=MyTopLevelIo.physRgbConfig,
      vgaTimingInfo=vgaTimingInfo,
      //timingInfo=LcvVgaTimingInfoMap.map("640x480@60"),
      fifoDepth=ctrlFifoDepth,
      fifoArrRamStyle="auto",
      //vivadoDebug=vivadoDebug,
    )
    //--------
    // BEGIN: later
    val gpu2d = Gpu2d(
      cfg=gpu2dCfg,
      //inVivado=true,
    )
    val gpu2dTest = Gpu2dTest(
      clkRate=clkRate,
      cfg=gpu2dCfg,
    )
    //val vidDith = LcvVideoDitherer(
    //  //fbSize2d=ElabVec2(x=640, y=480),
    //  rgbConfig=MyTopLevelIo.rgbConfig,
    //  fbSize2d=vgaTimingInfo.fbSize2d,
    //)
    //val vgaGrad = LcvVgaGradient(
    //  clkRate=clkRate,
    //  rgbConfig=MyTopLevelIo.rgbConfig,
    //  vgaTimingInfo=vgaTimingInfo,
    //  ctrlFifoDepth=ctrlFifoDepth,
    //  //vivadoDebug=vivadoDebug,
    //)
    //val vgaCtrlMisc = LcvVgaCtrlMiscIo()
    // END: later
    //--------
    // BEGIN: later

    def ctrlIo = vgaCtrl.io
    //def dithIo = vidDith.io
    //def gradIo = vgaGrad.io
    def gpuIo = gpu2d.io
    gpuIo.push << gpu2dTest.io.pop
    //--------

    //val snesCtrlReader = SnesCtrlReader(
    //  clkRate=clkRate,
    //  vivadoDebug=vivadoDebug,
    //)
    //io.snesCtrl <> snesCtrlReader.io.snesCtrl
    ////snesCtrlReader.io.pop.ready := True
    io.snesCtrl.outpClk := False
    io.snesCtrl.outpLatch := False
    val rLedArr = Reg(UInt(io.outpLedArr.size bits)) init(0x0)
    val rLedRgb = Reg(UInt(io.outpLedR.size bits)) init(0x0)
    rLedArr.addAttribute("MARK_DEBUG", "TRUE")
    rLedRgb.addAttribute("MARK_DEBUG", "TRUE")
    for (idx <- 0 to io.outpLedArr.size - 1) {
      io.outpLedArr(idx) := rLedArr(idx)
    }
    for (idx <- 0 to io.outpLedR.size - 1) {
      io.outpLedR(idx) := rLedRgb(idx)
      io.outpLedG(idx) := rLedRgb(idx)
      io.outpLedB(idx) := rLedRgb(idx)
    }
    ////val rReadSnesButtonsRisingEdge = Reg(Bool()) init(False)
    ////val rPastReadSnesButtons = Reg(Bool()) init(False)
    ////rReadSnesButtonsRisingEdge.addAttribute("MARK_DEBUG", "TRUE")
    ////rPastReadSnesButtons.addAttribute("MARK_DEBUG", "TRUE")
    ////rPastReadSnesButtons := io.inpReadSnesButtons

    ////val rResetReadSnesButtonsRisingEdge = Reg(Bool()) init(False)
    ////val rPastResetReadSnesButtons = Reg(Bool()) init(False)
    ////rResetReadSnesButtonsRisingEdge.addAttribute("MARK_DEBUG", "TRUE")
    ////rPastResetReadSnesButtons.addAttribute("MARK_DEBUG", "TRUE")
    ////rPastResetReadSnesButtons := io.inpResetReadSnesButtons

    //val rSnesCtrlReaderPopReady = Reg(Bool()) init(False)
    //rSnesCtrlReaderPopReady.addAttribute("MARK_DEBUG", "TRUE")
    //snesCtrlReader.io.pop.ready := rSnesCtrlReaderPopReady

    ////when (io.inpReadSnesButtons && !rPastReadSnesButtons) {
    ////  rReadSnesButtonsRisingEdge := True
    ////}
    ////when (io.inpResetReadSnesButtons && !rPastResetReadSnesButtons) {
    ////  rResetReadSnesButtonsRisingEdge := True
    ////}
    //val rSnesCntPopReady = Reg(ClkCnt(
    //  clkRate=clkRate,
    //  time=16.67 ms,
    //))
    //rSnesCntPopReady.init(rSnesCntPopReady.getZero)
    //when (snesCtrlReader.io.pop.valid) {
    //  //when (
    //  //  //rResetReadSnesButtonsRisingEdge
    //  //  rReadSnesButtonsRisingEdge
    //  //) {
    //    //rSnesCtrlReaderPopReady := True
    //  //}
    //  rSnesCntPopReady.incr()
    //  when (rSnesCntPopReady.overflowPipe(0)) {
    //    rSnesCtrlReaderPopReady := True
    //  }
    //  rLedArr(0) := !snesCtrlReader.io.pop.payload(SnesButtons.B)
    //  rLedArr(1) := !snesCtrlReader.io.pop.payload(SnesButtons.Y)
    //  rLedArr(2) := !snesCtrlReader.io.pop.payload(SnesButtons.A)
    //  rLedArr(3) := !snesCtrlReader.io.pop.payload(SnesButtons.X)

    //  rLedRgb(0) := !snesCtrlReader.io.pop.payload(SnesButtons.L)
    //  rLedRgb(1) := !snesCtrlReader.io.pop.payload(SnesButtons.R)
    //  rLedRgb(2) := !snesCtrlReader.io.pop.payload(SnesButtons.Start)
    //  rLedRgb(3) := !snesCtrlReader.io.pop.payload(SnesButtons.Select)
    //}
    //when (snesCtrlReader.io.pop.fire) {
    //  rSnesCtrlReaderPopReady := False
    //  //rReadSnesButtonsRisingEdge := False
    //  //rResetReadSnesButtonsRisingEdge := False
    //}

    //ctrlIo.en := gradIo.vgaCtrlIo.en
    //ctrlIo.push << gradIo.vgaCtrlIo.push
    ctrlIo.en := True
    //--------
    // BEGIN: later
    ctrlIo.push.valid := gpuIo.pop.valid
    ctrlIo.push.payload.r := gpuIo.pop.payload.col.r
    ctrlIo.push.payload.g := gpuIo.pop.payload.col.g
    ctrlIo.push.payload.b := gpuIo.pop.payload.col.b
    gpuIo.pop.ready := ctrlIo.push.ready
    // END: later
    //--------
    //ctrlIo.push.valid := True
    //ctrlIo.push.payload.r := 0xf
    //ctrlIo.push.payload.g := 0x8
    //ctrlIo.push.payload.b := 0x0
    //--------

    //gradIo.vgaCtrlIo.misc := ctrlIo.misc

    //dithIo.push << gradIo.vidDithIo.push
    ////gradIo.vidDithIo.outp := dithIo.outp
    //dithIo.pop >> gradIo.vidDithIo.pop 
    //gradIo.vidDithIo.info := dithIo.info

    //io.outpVga := vgaCtrl.io.phys
    for (idx <- 0 to io.outpVgaColR.size - 1) {
      io.outpVgaColR(idx) := ctrlIo.phys.col.r(idx)
      //io.outpLedR(idx) := ctrlIo.phys.col.r(idx)
    }
    for (idx <- 0 to io.outpVgaColG.size - 1) {
      io.outpVgaColG(idx) := ctrlIo.phys.col.g(idx)
      //io.outpLedG(idx) := ctrlIo.phys.col.g(idx)
    }
    for (idx <- 0 to io.outpVgaColB.size - 1) {
      io.outpVgaColB(idx) := ctrlIo.phys.col.b(idx)
      //io.outpLedB(idx) := ctrlIo.phys.col.b(idx)
    }

    io.outpVgaHsync := ctrlIo.phys.hsync
    io.outpVgaVsync := ctrlIo.phys.vsync
  }
  //--------
}
// END: code for outputting .edif
//--------

object MyConfigForYosys {
  def spinal = SpinalConfig(
    targetDirectory="hw/gen",
    defaultConfigForClockDomains=ClockDomainConfig(
      //resetActiveLevel=HIGH,
      resetKind=BOOT,
    ),
    onlyStdLogicVectorAtTopLevelIo=true,
  )
}
object MyTopLevelForYosysVerilog extends App {
  MyConfigForYosys.spinal.generateVerilog(MyTopLevelForYosys())
}

object MyTopLevelForYosysVhdl extends App {
  MyConfigForYosys.spinal.generateVhdl(MyTopLevelForYosys())
  //val report = SpinalVhdl(new MyTopLevel())
  //report.printPruned()
  //val test = PipeSkidBuf(UInt(3 bits))
}
//--------
// BEGIN: code for input to Vivado
case class MyTopLevelForYosysBb() extends BlackBox {
  //--------
  val io = MyTopLevelForYosysIo()
  //--------
}
// END: code for input to Vivado
case class MyTopLevelForVivado() extends Component {
  //--------
  val io = MyTopLevelForYosysIo()
  //--------
  val yosys = MyTopLevelForYosysBb()
  //io <> yosys.io
  io.snesCtrl <> yosys.io.snesCtrl
  for (idx <- 0 until io.outpVgaColR.size) {
    io.outpVgaColR(idx) := yosys.io.outpVgaColR(idx)
  }
  for (idx <- 0 until io.outpVgaColG.size) {
    io.outpVgaColG(idx) := yosys.io.outpVgaColG(idx)
  }
  for (idx <- 0 until io.outpVgaColB.size) {
    io.outpVgaColB(idx) := yosys.io.outpVgaColB(idx)
  }
  io.outpVgaHsync := yosys.io.outpVgaHsync
  io.outpVgaVsync := yosys.io.outpVgaVsync
  for (idx <- 0 until io.outpLedR.size) {
    io.outpLedR(idx) := yosys.io.outpLedR(idx)
    io.outpLedG(idx) := yosys.io.outpLedG(idx)
    io.outpLedB(idx) := yosys.io.outpLedB(idx)
    io.outpLedArr(idx) := yosys.io.outpLedArr(idx)
  }
  //--------
}
object MyTopLevelForVivadoVerilog extends App {
  MyConfigForYosys.spinal.generateVerilog(MyTopLevelForVivado())
}

object MyTopLevelForVivadoVhdl extends App {
  MyConfigForYosys.spinal.generateVhdl(MyTopLevelForVivado())
  //val report = SpinalVhdl(new MyTopLevel())
  //report.printPruned()
  //val test = PipeSkidBuf(UInt(3 bits))
}
