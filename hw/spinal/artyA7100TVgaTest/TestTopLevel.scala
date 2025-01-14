package artyA7100TVgaTest

import spinal.core._
import spinal.lib._
import spinal.lib.eda.altera.QuartusFlow
import spinal.lib.eda.altera.QuartusProject
import spinal.lib.graphic.Rgb
import spinal.lib.graphic.RgbConfig
import spinal.lib.graphic.vga.VgaCtrl
import spinal.lib.graphic.vga.Vga
import spinal.lib.graphic.vga.VgaTimingsHV
import libcheesevoyage._
import libcheesevoyage.general._
import libcheesevoyage.gfx._
import libcheesevoyage.hwdev._
import scala.collection.mutable.ArrayBuffer
//import libcheesevoyage.math.trySpinal._

//case class MyTopLevel(width: Int=2, size: Int=4) extends Component {
//  val io = new Bundle {
//    val va = VectorAluIo(width=width, size=size)
//    //val va = new Bundle {
//    //  val vec = Vec(AluIo(width=width), size)
//    //}
//    val sel = in UInt(log2Up(size) bits)
//    val result = out UInt(width bits)
//  }
//
//  val dut = VectorAlu(width=width, size=size)
//  io.va.vec <> dut.io.vec
//  //dut.io.inp := io.va.inp
//  //io.va.outp := dut.io.outp
//  //io.result := io.va.outp(io.sel).result
//  io.result := io.va.vec(io.sel).outp.result
//
//  //dut.io.inp := io.inp
//  //io.outp := dut.io.outp
//}
//
//case class Pll50To100() extends BlackBox {
//  val io = new Bundle {
//    val refclk = in Bool()
//    val rst = in Bool()
//    val outclk_0 = out Bool()
//    val locked = out Bool()
//  }
//  noIoPrefix()
//}
//case class pll50To100p7Mod() extends BlackBox {
//  val io = new Bundle {
//    val refclk = in Bool()
//    val rst = in Bool()
//    val outclk_0 = out Bool()
//    val locked = out Bool()
//  }
//  noIoPrefix()
//}
//case class PwrOnRst() extends BlackBox {
//  val io = new Bundle {
//    val refclk = in Bool()
//    val rst = out Bool()
//  }
//  noIoPrefix()
//}
case class my_core_clk_wiz() extends BlackBox {
  val io = new Bundle {
    val clk_in1_0 = in Bool()
    //val reset = in Bool()
    val clk_out1_0 = out Bool()
    //val clk_out2_0 = out Bool()
    val locked_0 = out Bool()
  }
  noIoPrefix()
}
//case class vga_clk_wiz() extends BlackBox {
//  val io = new Bundle {
//    val clk_in1_0 = in Bool()
//    //val reset = in Bool()
//    val clk_out1_0 = out Bool()
//    val locked_0 = out Bool()
//  }
//  noIoPrefix()
//}

//case class ila_vga_phys() extends BlackBox {
//  val io = new Bundle {
//    //--------
//    val trig_in_ack = out Bool()
//    val trig_in = in Bool()
//    val probe0 = in UInt(MyTopLevelIo.physRgbConfig.rWidth bits)
//    val probe1 = in UInt(MyTopLevelIo.physRgbConfig.gWidth bits)
//    val probe2 = in UInt(MyTopLevelIo.physRgbConfig.bWidth bits)
//    val probe3 = in UInt(1 bits)
//    val probe4 = in UInt(1 bits)
//    //--------
//    val physColR = probe0
//    val physColG = probe1
//    val physColB = probe2
//    val physHsync = probe3
//    val physVsync = probe4
//    //--------
//  }
//  noIoPrefix()
//}


object MyTopLevelIo {
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

case class MyTopLevelIo(
  //rgbConfig: RgbConfig
  optIncludeSnesCtrl: Boolean=true
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
  val snesCtrl = (optIncludeSnesCtrl) generate (
    SnesCtrlIo()
  )
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

  //val outpLedR = new ArrayBuffer[Bool]()
  //val outpLedG = new ArrayBuffer[Bool]()
  //val outpLedB = new ArrayBuffer[Bool]()
  //val outpLedArr = new ArrayBuffer[Bool]()
  //for (idx <- 0 to 4 - 1) {
  //  outpLedR += out Bool()
  //  outpLedR.last.setName(f"io_led$idx" + "_r")
  //  outpLedG += out Bool()
  //  outpLedG.last.setName(f"io_led$idx" + "_g")
  //  outpLedB += out Bool()
  //  outpLedB.last.setName(f"io_led$idx" + "_b")
  //  outpLedArr += out Bool()
  //  outpLedArr.last.setName(f"io_led$idx")
  //}

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
//case class DbgArrElem() extends Bundle {
//  val ctrlPushValid = Bool()
//  val ctrlPushReady = Bool()
//  //val ctrlPushFire = Bool()
//  val ctrlPushCol = Rgb(MyTopLevelIo.physRgbConfig)
//  val ctrlPhys = LcvVgaPhys(MyTopLevelIo.physRgbConfig)
//
//  val dithPushValid = Bool()
//  //val dithPushReady = Bool()
//  //val dithPushFire = Bool()
//  val dithPushCol = Rgb(MyTopLevelIo.rgbConfig)
//  //val dithPopValid = Bool()
//  //val dithPopReady = Bool()
//  //val dithPopFire = Bool()
//  //val dithPopCol = Rgb(MyTopLevelIo.physRgbConfig)
//  val dithOutpCol = Rgb(MyTopLevelIo.physRgbConfig)
//}
case class DbgArrElem() extends Bundle {
  val unitCntLatchOverflowPipe1 = Bool()
  val unitCntWaitToClkGenOverflowPipe1 = Bool()
  val unitCntDoClkGenOverflowPipe2 = Bool()
  val unitCntDoClkGenOverflowPipe1 = Bool()

  val snesCtrl_clk = Bool()
  val snesCtrl_latch = Bool()
  val snesCtrl_data = Bool()
  val readerState = SnesCtrlReaderState()
}
case class MyTopLevel(
  //rgbConfig: RgbConfig=RgbConfig(rWidth=4, gWidth=4, bWidth=4),
) extends Component {
  //--------
  val io = MyTopLevelIo(
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
  //def clkRate = 175.0 MHz
  //def coreClkRate = 140.0 MHz  // 400x225@60 (20x pixel clock)
  //def clkRate = 150.0 MHz // 640x480@60 (6x pixel clock)
  //def clkRate = 126.0 MHz   // 640x360@60 (7x pixel clock)
  //def clkRate = 144.0 MHz   // 640x360@60 (8x pixel clock)
  def coreClkRate = (
    //50.0 MHz
    //125.0 MHz,
    //140.0 MHz
    //118.25 MHz    // 1600x900@60 (1x pixel clock)
    //118.25 MHz    // 1600x900@60 (1x pixel clock)
    148.5 MHz    // 1920x1080 (1x pixel clock)
    //150.0 MHz
    //140.0 MHz
    //147.8125 MHz
  )
  //  // NOTE: couldn't get the `core_clk_wiz` to do this exact clock rate.
  //  // Instead it's 143.75 MHz
  //def clkRate = 125.0 MHz // 640x480@60 (5x pixel clock)
  //def clkRate = 100.0 MHz // 640x480@60 (4x pixel clock)
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
  //--------
  val tempVgaTimingInfo = (
    //LcvVgaTimingInfoMap.map("640x480@60")
    //LcvVgaTimingInfoMap.map("320x240@60")
    //LcvVgaTimingInfoMap.map("640x360@60")
    //LcvVgaTimingInfoMap.map("400x225@60")
    //LcvVgaTimingInfoMap.map("1600x900@60")
    LcvVgaTimingInfoMap.map("1920x1080@60")
  )
  val vgaTimingInfo = (
    //LcvVgaTimingInfo(
    //  pixelClk=tempVgaTimingInfo.pixelClk,
    //  htiming=tempVgaTimingInfo.htiming,
    //  vtiming=tempVgaTimingInfo.vtiming,
    //  hPolarity=true,
    //  vPolarity=true,
    //)
    tempVgaTimingInfo
  )
  def vgaClkRate = (
    coreClkRate
    //236.5 MHz // 1600x900@60 (2x pixel clock)
    //297.0 MHz // 1920x1080@60 (2x pixel clock)
    //297.5 MHz // 1920x1080@60 (2x pixel clock)
    //150.0 MHz
    //125.0 MHz
  )
  //def vgaRealClkRate = (
  //  236.25 MHz  // 1600x900@60 (~2x pixel clock)
  //)

  //def vgaClkRate = vgaTimingInfo.pixelClk * 2
  //val vgaTimingInfo = LcvVgaTimingInfo(
  //  //pixelClk=12.5 MHz,
  //  //pixelClk=25.175 MHz,
  //  pixelClk=6.0 MHz,
  //  //htiming=LcvVgaTimingHv(
  //  //  visib=640 / 2,
  //  //  front=16 / 2,
  //  //  sync=96 / 2,
  //  //  back=48 / 2
  //  //),
  //  htiming=LcvVgaTimingHv(
  //    visib=320,
  //    front=8,
  //    sync=32,
  //    back=40,
  //  ),
  //  //vtiming=LcvVgaTimingHv(
  //  //  visib=480 / 2,
  //  //  front=10 / 2,
  //  //  sync=2 / 2,
  //  //  back=33 / 2
  //  //),
  //  vtiming=LcvVgaTimingHv(
  //    visib=240,
  //    front=3,
  //    sync=4,
  //    back=6,
  //  ),
  //)
  //val vgaTimingInfo = LcvVgaTimingInfoMap.map("1440x900@60")
  //val vgaTimingInfo = LcvVgaTimingInfoMap.map("1600x900@60")
  //val vgaTimingInfo = LcvVgaTimingInfoMap.map("1920x1080@60")
  def vivadoDebug = true
  def gpu2dPhysFbSize2dScale = ElabVec2[Int](
    //x=1,
    //y=1,
    //x=2,
    //y=2,
    //x=4,
    //y=4,
    x=5,
    y=5,
  )
  def gpu2dIntnlFbSize2d = ElabVec2[Int](
    x=vgaTimingInfo.fbSize2d.x / gpu2dPhysFbSize2dScale.x,
    y=vgaTimingInfo.fbSize2d.y / gpu2dPhysFbSize2dScale.y,
  )
  def gpu2dBgTileSize2dPow=ElabVec2[Int](
    x=log2Up(16),
    y=log2Up(16),
    //x=log2Up(8),
    //y=log2Up(8),
    //x=log2Up(4),
    //y=log2Up(4),
    //x=log2Up(2),
    //y=log2Up(2),
  )
  def gpu2dParams = DefaultGpu2dParams(
    rgbConfig=MyTopLevelIo.physRgbConfig,
    intnlFbSize2d=gpu2dIntnlFbSize2d,
    physFbSize2dScale=gpu2dPhysFbSize2dScale,
    //intnlFbSize2d=ElabVec2[Int](
    //  x=vgaTimingInfo.fbSize2d.x,
    //  y=vgaTimingInfo.fbSize2d.y,
    //),
    //physFbSize2dScalePow=ElabVec2[Int](
    //  x=log2Up(1),
    //  y=log2Up(1),
    //  //x=log2Up(2),
    //  ////y=log2Up(2),
    //  //y=log2Up(2),
    //),
    //physFbSize2dScale=ElabVec2[Int](
    //  x=1,
    //  y=1,
    //),
    bgTileSize2dPow=gpu2dBgTileSize2dPow,
    objTileSize2dPow=ElabVec2[Int](
      x=log2Up(16),
      y=log2Up(16),
      //x=log2Up(8),
      //y=log2Up(8),
      //x=log2Up(4),
      //y=log2Up(4),
      //x=log2Up(2),
      //y=log2Up(2),
    ),
    objTileWidthRshift=(
      //0
      //1
      2
    ),
    objAffineTileSize2dPow=ElabVec2[Int](
      x=log2Up(64),
      y=log2Up(64),
      //x=log2Up(16),
      //y=log2Up(16),
      //x=log2Up(8),
      //y=log2Up(8),
      //x=log2Up(4),
      //y=log2Up(4),
      //x=log2Up(2),
      //y=log2Up(2),
    ),
    objAffineTileWidthRshift=(
      //0
      //1
      //2
      //3
      4
      //5
    ),
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
    numObjsPow=(
      //log2Up(128)
      log2Up(8)
    ),
    numObjsAffinePow=(
      log2Up(32)
      //log2Up(24)
    ),
    //numBgTilesPow=Some(log2Up(256)),
    //numBgTilesPow=Some(log2Up(2)),
    numBgTiles=(
      //Some(16)
      //Some(320 * 240)
      // for double buffering
      //Some(
      //  vgaTimingInfo.fbSize2d.x
      //  * vgaTimingInfo.fbSize2d.y
      //  * 2
      //)
      Some(
        //320 * 240
        ////640 * 480
        ////640 * 360
        ////(
        ////  vgaTimingInfo.fbSize2d.x
        ////  * vgaTimingInfo.fbSize2d.y
        ////)
        /// (1 << (gpu2dBgTileSize2dPow.x + gpu2dBgTileSize2dPow.y))
        //16
        256
        //(
        //  gpu2dIntnlFbSize2d.x * gpu2dIntnlFbSize2d.y
        //) / (
        //  1 << (gpu2dBgTileSize2dPow.x + gpu2dBgTileSize2dPow.y)
        //)
      )
      //Some(
      //  8 * 8
      //)
    ),
    //numObjTilesPow=None,
    numObjTiles=(
      Some(128)
      //Some(64)
    ),
    numObjAffineTiles=(
      //Some(32)
      //Some(24)
      Some(16)
    ),
    numColsInBgPalPow=(
      //log2Up(256)
      log2Up(64)
    ),
    numColsInObjPalPow=(
      //log2Up(256)
      //log2Up(128)
      log2Up(64)
    ),
    noColorMath=(
      //false
      true
    ),
    noAffineBgs=(
      //false
      true
    ),
    noAffineObjs=(
      //false
      true
    ),
    //noAffineObjs=false,
    //fancyObjPrio=false,
    fancyObjPrio=(
      //false
      true
    ),

    //bgTileArrRamStyle="block",
    //objTileArrRamStyle="block",
    //bgEntryArrRamStyle="block",
    ////bgAttrsArrRamStyle="block",

    //objAttrsArrRamStyle="block",
    //bgPalEntryArrRamStyle="block",
    //objPalEntryArrRamStyle="block",
    //lineArrRamStyle="block",
    bgTileArrRamStyle="block",
    objTileArrRamStyle="block",
    objAffineTileArrRamStyle="block",
    bgEntryArrRamStyle="block",
    //bgAttrsArrRamStyle="block",
    objAttrsArrRamStyle="auto",
    bgPalEntryArrRamStyle="auto",
    objPalEntryArrRamStyle="auto",
    //bgLineArrRamStyle="block",
    //objLineArrRamStyle="block",
    //combineLineArrRamStyle="block",
    lineArrRamStyle="block",
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
    val coreClkWiz = new my_core_clk_wiz
    //pll.io.refclk := io.clk50Mhz
    //pll.io.refclk := clockDomain.clock
    coreClkWiz.io.clk_in1_0 := clockDomain.readClockWire
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
      frequency=FixedFrequency(coreClkRate)
    )

    // Drive clock and reset of the `coreClockDomain` previously created
    coreClockDomain.clock := coreClkWiz.io.clk_out1_0
    //coreClockDomain.reset := ResetCtrl.asyncAssertSyncDeassert(
    //  //input=io.rst || !pll.io.locked,
    //  //input=(!pll.io.locked),
    //  //input=(pllRstCnt =/= 0) || !pll.io.locked,
    //  input=tempRst || !pll.io.locked,
    //  clockDomain=coreClockDomain,
    //)
    //val vgaClkWiz = new vga_clk_wiz
    ////pll.io.refclk := io.clk50Mhz
    ////pll.io.refclk := clockDomain.clock
    //vgaClkWiz.io.clk_in1_0 := clockDomain.readClockWire
    ////val tempRst = (pllRstCnt =/= 0)
    ////pll.io.reset := tempRst

    //val pwrOnRst = new PwrOnRst()
    //pwrOnRst.io.refclk := io.clk50Mhz
    //pll.io.rst := pwrOnRst.io.rst

    //--------
    // Create a new clock domain named "vga"
    //val vgaClockDomain = ClockDomain.internal(
    //  name="vga",
    //  config=ClockDomainConfig(
    //    //resetActiveLevel = HIGH
    //    resetKind=BOOT,
    //  ),
    //  frequency=FixedFrequency(vgaRealClkRate)
    //)

    //// Drive clock and reset of the `vgaClockDomain` previously created
    ////vgaClockDomain.clock := vgaClkWiz.io.clk_out1_0
    //vgaClockDomain.clock := coreClkWiz.io.clk_out2_0
    val vgaCtrlFifoDepth = 10
    //--------

    ////val myGpuToVgaFifoPush = Stream(Rgb(c=gpu2dParams.rgbConfig))
    ////val myGpuToVgaFifoPop = cloneOf(myGpuToVgaFifoPush)
    //////val myGpuToVgaFifoPush = cloneOf(myGpuToVgaFifoPushFinal)
    //////val myGpuToVgaFifoPop = cloneOf(myGpuToVgaFifoPopFinal)

    ////val myGpuPopStm = Stream(Rgb(c=gpu2dParams.rgbConfig))
    //val gpuToVgaFifoCC = StreamFifoCC(
    //  dataType=Rgb(c=gpu2dParams.rgbConfig),
    //  //push=myGpuToVgaFifoPush,
    //  //pop=myGpuToVgaFifoPop,
    //  depth=128,
    //  pushClock=coreClockDomain,
    //  popClock=vgaClockDomain,
    //)
    ////val myVgaToTestFifoPush = Stream(Bits(2 bits))
    ////val myVgaToTestFifoPop = cloneOf(myVgaToTestFifoPush)
    //val vgaToTestFifoCC = StreamFifoCC(
    //  dataType=(
    //    //LcvVgaState.craft()
    //    Bits(2 bits),
    //  ),
    //  //push=myVgaToTestFifoPush,
    //  //pop=myVgaToTestFifoPop,
    //  depth=16,
    //  pushClock=vgaClockDomain,
    //  popClock=coreClockDomain,
    //)
  }

  //val vga = new ClockingArea(clkCtrl.vgaClockDomain) {
  //  val vgaCtrl = LcvVgaCtrl(
  //    clkRate=vgaClkRate,
  //    rgbConfig=MyTopLevelIo.physRgbConfig,
  //    vgaTimingInfo=vgaTimingInfo,
  //    //timingInfo=LcvVgaTimingInfoMap.map("640x480@60"),
  //    fifoDepth=clkCtrl.vgaCtrlFifoDepth,
  //    fifoArrRamStyle="distributed",
  //    //vivadoDebug=vivadoDebug,
  //  )
  //  vgaCtrl.io.push <-/< clkCtrl.gpuToVgaFifoCC.io.pop
  //  val myVpipeSPipe2Stm = (
  //    //Stream(Bits(vgaCtrl.io.misc.vpipeSPipe2.getWidth))
  //    //cloneOf(clkCtrl.myVgaToTestFifoPush)
  //    cloneOf(clkCtrl.vgaToTestFifoCC.io.push)
  //  )
  //  //val myVpipeSPipe2TranslateStm = (
  //  //  //Stream(Bits(vgaCtrl.io.misc.vpipeSPipe2.getWidth))
  //  //  //cloneOf(clkCtrl.myVgaToTestFifoPush)
  //  //  cloneOf(myVpipeSPipe2PushStm)
  //  //)
  //  clkCtrl.vgaToTestFifoCC.io.push <-/< myVpipeSPipe2Stm
  //  //clkCtrl.vgaToTestFifoCC.io.push.valid := True
  //  //clkCtrl.vgaToTestFifoCC.io.push.payload := (
  //  //  vgaCtrl.io.misc.vpipeSPipe2.asBits
  //  //)
  //  myVpipeSPipe2Stm.valid := True
  //  myVpipeSPipe2Stm.payload := (
  //    vgaCtrl.io.misc.vpipeSPipe2.asBits
  //  )

  //  //myVpipeSPipe2TranslateStm.translateInto(
  //  //  into=myVpipeSPipe2PushStm
  //  //)(
  //  //  dataAssignment=(o, i) => {
  //  //    
  //  //  }
  //  //)
  //  //def myVpipeSPipe2 = //vgaCtrl.io.misc.vpipeSPipe2
  //  //def myPayload = clkCtrl.vgaToTestFifoCC.io.push.payload
  //  //def myPayload = myVpipeSPipe2Stm.payload
  //  //switch (myVpipeSPipe2) {
  //  //  is (LcvVgaState.front) {
  //  //    myPayload := B"2'b00"
  //  //  }
  //  //  is (LcvVgaState.sync) {
  //  //    myPayload := B"2'b01"
  //  //  }
  //  //  is (LcvVgaState.back) {
  //  //    myPayload := B"2'b10"
  //  //  }
  //  //  is (LcvVgaState.visib) {
  //  //    myPayload := B"2'b11"
  //  //  }
  //  //}
  //}

  // Create a `ClockingArea` which will be under the effect of
  // `clkCtrl.coreClockDomain` 
  val core = new ClockingArea(clkCtrl.coreClockDomain) {
    ////val gpuToVgaFifo = StreamFifo(
    ////  dataType=Rgb(c=gpu2dParams.rgbConfig),
    ////  //push=myGpuToVgaFifoPush,
    ////  //pop=myGpuToVgaFifoPop,
    ////  depth=16,
    ////  //pushClock=coreClockDomain,
    ////  //popClock=vgaClockDomain,
    ////)
    ////val vgaToTestFifo = StreamFifo(
    ////  dataType=(
    ////    //LcvVgaState.craft()
    ////    Bits(2 bits),
    ////  ),
    ////  //push=myVgaToTestFifoPush,
    ////  //pop=myVgaToTestFifoPop,
    ////  depth=16,
    ////  //pushClock=vgaClockDomain,
    ////  //popClock=coreClockDomain,
    ////)
    ////val pixelClk = 25.0 
    //////def pixelClk = 25.175
    ////def ctrlFifoDepth = 100
    ////val ctrlFifoDepth = 64
    ////val dbgTrigger = Reg(Bool()) init(False) addAttribute("keep")
    ////dbgTrigger := True
    ////io.outpDbgTrigger0 := dbgTrigger
    ////--------
    //val vgaCtrl = LcvVgaCtrlPipelined(
    //  clkRate=vgaClkRate,
    //  rgbConfig=MyTopLevelIo.physRgbConfig,
    //  vgaTimingInfo=vgaTimingInfo,
    //  //timingInfo=LcvVgaTimingInfoMap.map("640x480@60"),
    //  fifoDepth=clkCtrl.vgaCtrlFifoDepth,
    //  fifoArrRamStyle="distributed",
    //  //vivadoDebug=vivadoDebug,
    //)
    ////vgaCtrl.io.push <-/< clkCtrl.gpuToVgaFifoCC.io.pop
    ////--------
    ////val vgaCtrl = VgaCtrl(
    ////  rgbConfig=gpu2dParams.rgbConfig,
    ////)
    ////--------
    ////val myVpipeSPipe2Stm = (
    ////  //Stream(Bits(vgaCtrl.io.misc.vpipeSPipe2.getWidth))
    ////  //cloneOf(clkCtrl.myVgaToTestFifoPush)
    ////  cloneOf(clkCtrl.vgaToTestFifoCC.io.push)
    ////)
    ////--------
    //////val myVpipeSPipe2TranslateStm = (
    //////  //Stream(Bits(vgaCtrl.io.misc.vpipeSPipe2.getWidth))
    //////  //cloneOf(clkCtrl.myVgaToTestFifoPush)
    //////  cloneOf(myVpipeSPipe2PushStm)
    //////)
    ////clkCtrl.vgaToTestFifoCC.io.push <-/< myVpipeSPipe2Stm
    //////clkCtrl.vgaToTestFifoCC.io.push.valid := True
    //////clkCtrl.vgaToTestFifoCC.io.push.payload := (
    //////  vgaCtrl.io.misc.vpipeSPipe2.asBits
    //////)
    ////myVpipeSPipe2Stm.valid := True
    ////myVpipeSPipe2Stm.payload := (
    ////  vgaCtrl.io.misc.vpipeSPipe2.asBits
    ////)
    ////--------
    //def dbgPipeMemRmw = (
    //  //true
    //  false
    //)
    //val gpu2d = Gpu2d(
    //  params=gpu2dParams,
    //  //inVivado=true,
    //  dbgPipeMemRmw=dbgPipeMemRmw,
    //)
    //val gpu2dTest = Gpu2dTest(
    //  clkRate=coreClkRate,
    //  params=gpu2dParams,
    //  dbgPipeMemRmw=dbgPipeMemRmw,
    //  optRawSnesButtons=false,
    //)
    ////val vidDith = LcvVideoDitherer(
    ////  //fbSize2d=ElabVec2(x=640, y=480),
    ////  rgbConfig=MyTopLevelIo.rgbConfig,
    ////  fbSize2d=vgaTimingInfo.fbSize2d,
    ////)
    ////val vgaGrad = LcvVgaGradient(
    ////  clkRate=clkRate,
    ////  rgbConfig=MyTopLevelIo.rgbConfig,
    ////  vgaTimingInfo=vgaTimingInfo,
    ////  ctrlFifoDepth=ctrlFifoDepth,
    ////  //vivadoDebug=vivadoDebug,
    ////)
    ////val vgaCtrlMisc = LcvVgaCtrlMiscIo()


    ////def ctrlIo = vga.vgaCtrl.io
    //def ctrlIo = vgaCtrl.io
    ////ctrlIo.softReset := RegNext(False) init(True)
    ////--------
    //// BEGIN: `VgaCtrl` stuff
    ////vgaTimingInfo.driveSpinalVgaTimings(
    ////  clkRate=coreClkRate,
    ////  spinalVgaTimings=ctrlIo.timings,
    ////)
    //// END: `VgaCtrl` stuff
    ////--------
    ////ctrlIo.timings.h.colorStart := 0
    ////ctrlIo.timings.h.colorEnd := vgaTimingInfo.htiming.visib - 1
    ////ctrlIo.timings.h.syncStart := vgaTimingInfo.htiming.visib
    ////ctrlIo.timings.h.syncEnd := (
    ////  vgaTimingInfo.htiming.visib
    ////  + vgaTimingInfo.htiming.front
    ////  + vgaTimingInfo.htiming.sync
    ////  + vgaTimingInfo.htiming.back
    ////  - 1
    ////)
    ////ctrlIo.timings.h.polarity := False
    ////ctrlIo.timings.v.colorStart := 0
    ////ctrlIo.timings.v.colorEnd := vgaTimingInfo.vtiming.visib - 1
    ////ctrlIo.timings.v.syncStart := vgaTimingInfo.vtiming.visib
    ////ctrlIo.timings.v.syncEnd := (
    ////  vgaTimingInfo.vtiming.visib
    ////  + vgaTimingInfo.vtiming.front
    ////  + vgaTimingInfo.vtiming.sync
    ////  + vgaTimingInfo.vtiming.back
    ////  - 1
    ////)
    ////ctrlIo.timings.v.polarity := False
    ////def dithIo = vidDith.io
    ////def gradIo = vgaGrad.io
    //def gpuIo = gpu2d.io
    //gpuIo.push << gpu2dTest.io.pop
    ////gpu2dTest.io.gpu2dPopReady := gpuIo.pop.ready

    ////gpu2dTest.io.vgaSomeVpipeS := (
    ////  RegNext(gpu2dTest.io.vgaSomeVpipeS)
    ////  //init(gpu2dTest.io.vgaSomeVpipeS.getZero)
    ////  init(LcvVgaState.front)
    ////)
    ////val tempGpuPopStm = cloneOf(gpuIo.pop)
    ////--------
    //// BEGIN: `VgaCtrl` stuff
    //val myGpuPopStm = cloneOf(gpuIo.pop)
    //when (
    //  myGpuPopStm.valid
    //  //&& myGpuPopStm.physPosInfo.nextPos.x === 0
    //  && myGpuPopStm.physPosInfo.nextPos.y === 0
    //) {
    //  gpu2dTest.io.vgaSomeVpipeS := (
    //    LcvVgaState.front
    //  )
    //} otherwise {
    //  gpu2dTest.io.vgaSomeVpipeS := (
    //    LcvVgaState.visib
    //  )
    //}
    //// END: `VgaCtrl` stuff
    ////--------
    ////val mySomeVpipeSStm = cloneOf(clkCtrl.vgaToTestFifoCC.io.pop)
    ////--------
    ////clkCtrl.vgaToTestFifoCC.io.pop.translateInto(
    ////  into=mySomeVpipeSStm
    ////)

    ////mySomeVpipeSStm <-/< clkCtrl.vgaToTestFifoCC.io.pop
    ////when (mySomeVpipeSStm.fire) {
    ////  def mySomeVpipeS = gpu2dTest.io.vgaSomeVpipeS
    ////  mySomeVpipeS.assignFromBits(
    ////    mySomeVpipeSStm.payload
    ////  )
    ////  //switch (mySomeVpipeSStm.payload) {
    ////  //  is (B"2'b00") {
    ////  //    mySomeVpipeS := LcvVgaState.front
    ////  //  }
    ////  //  is (B"2'b01") {
    ////  //    mySomeVpipeS := LcvVgaState.sync
    ////  //  }
    ////  //  is (B"2'b10") {
    ////  //    mySomeVpipeS := LcvVgaState.back
    ////  //  }
    ////  //  is (B"2'b11") {
    ////  //    mySomeVpipeS := LcvVgaState.visib
    ////  //  }
    ////  //}
    ////  //gpu2dTest.io.vgaSomeVpipeS.assignFromBits(
    ////  //  clkCtrl.vgaToTestFifoCC.io.pop.payload//.asBits
    ////  //)
    ////}
    ////mySomeVpipeSStm.ready := True

    //////gpu2dTest.io.gpu2dPopReady.addAttribute("MARK_DEBUG", "TRUE")
    ////gpu2dTest.io.vgaSomeVpipeS.addAttribute("MARK_DEBUG", "TRUE")
    ////gpu2dTest.rHoldCnt.addAttribute("MARK_DEBUG", "TRUE")
    ////gpu2dTest.tempBgAttrs0PushValid.addAttribute("MARK_DEBUG", "TRUE")
    ////gpu2dTest.io.pop.objAttrsPush.valid.addAttribute("MARK_DEBUG", "TRUE")
    ////gpu2dTest.rDidSnesFire.addAttribute("MARK_DEBUG", "TRUE")
    ////gpu2dTest.tempBgAttrs.scroll.addAttribute("MARK_DEBUG", "TRUE")
    ////gpu2dTest.tempObjAttrs.pos.addAttribute("MARK_DEBUG", "TRUE")
    ////gpu2dTest.rObjAttrsCnt.addAttribute("MARK_DEBUG", "TRUE")

    //io.snesCtrl <> gpu2dTest.io.snesCtrl

    ////val snesCtrlReader = SnesCtrlReader(
    ////  clkRate=clkRate,
    ////  vivadoDebug=vivadoDebug,
    ////)
    ////io.snesCtrl <> snesCtrlReader.io.snesCtrl
    //////snesCtrlReader.io.pop.ready := True

    //////io.snesCtrl.outpClk := False
    //////io.snesCtrl.outpLatch := False

    //////val rLedArr = Reg(UInt(io.outpLedArr.size bits)) init(0x0)
    //////val rLedRgb = Reg(UInt(io.outpLedR.size bits)) init(0x0)
    //////rLedArr.addAttribute("MARK_DEBUG", "TRUE")
    //////rLedRgb.addAttribute("MARK_DEBUG", "TRUE")
    //////for (idx <- 0 to io.outpLedArr.size - 1) {
    //////  io.outpLedArr(idx) := rLedArr(idx)
    //////}
    //////for (idx <- 0 to io.outpLedR.size - 1) {
    //////  io.outpLedR(idx) := rLedRgb(idx)
    //////  io.outpLedG(idx) := rLedRgb(idx)
    //////  io.outpLedB(idx) := rLedRgb(idx)
    //////}

    ////////val rReadSnesButtonsRisingEdge = Reg(Bool()) init(False)
    ////////val rPastReadSnesButtons = Reg(Bool()) init(False)
    ////////rReadSnesButtonsRisingEdge.addAttribute("MARK_DEBUG", "TRUE")
    ////////rPastReadSnesButtons.addAttribute("MARK_DEBUG", "TRUE")
    ////////rPastReadSnesButtons := io.inpReadSnesButtons

    ////////val rResetReadSnesButtonsRisingEdge = Reg(Bool()) init(False)
    ////////val rPastResetReadSnesButtons = Reg(Bool()) init(False)
    ////////rResetReadSnesButtonsRisingEdge.addAttribute("MARK_DEBUG", "TRUE")
    ////////rPastResetReadSnesButtons.addAttribute("MARK_DEBUG", "TRUE")
    ////////rPastResetReadSnesButtons := io.inpResetReadSnesButtons

    ////val rSnesCtrlReaderPopReady = Reg(Bool()) init(False)
    //////rSnesCtrlReaderPopReady.addAttribute("MARK_DEBUG", "TRUE")
    ////snesCtrlReader.io.pop.ready := rSnesCtrlReaderPopReady

    ////////when (io.inpReadSnesButtons && !rPastReadSnesButtons) {
    ////////  rReadSnesButtonsRisingEdge := True
    ////////}
    ////////when (io.inpResetReadSnesButtons && !rPastResetReadSnesButtons) {
    ////////  rResetReadSnesButtonsRisingEdge := True
    ////////}
    ////val rSnesCntPopReady = Reg(ClkCnt(
    ////  clkRate=clkRate,
    ////  time=16.67 ms,
    ////))
    ////rSnesCntPopReady.init(rSnesCntPopReady.getZero)
    ////when (snesCtrlReader.io.pop.valid) {
    ////  //when (
    ////  //  //rResetReadSnesButtonsRisingEdge
    ////  //  rReadSnesButtonsRisingEdge
    ////  //) {
    ////    //rSnesCtrlReaderPopReady := True
    ////  //}
    ////  rSnesCntPopReady.incr()
    ////  when (rSnesCntPopReady.overflowPipe(0)) {
    ////    rSnesCtrlReaderPopReady := True
    ////  }
    ////  //rLedArr(0) := !snesCtrlReader.io.pop.payload(SnesButtons.B)
    ////  //rLedArr(1) := !snesCtrlReader.io.pop.payload(SnesButtons.Y)
    ////  //rLedArr(2) := !snesCtrlReader.io.pop.payload(SnesButtons.A)
    ////  //rLedArr(3) := !snesCtrlReader.io.pop.payload(SnesButtons.X)

    ////  //rLedRgb(0) := !snesCtrlReader.io.pop.payload(SnesButtons.L)
    ////  //rLedRgb(1) := !snesCtrlReader.io.pop.payload(SnesButtons.R)
    ////  //rLedRgb(2) := !snesCtrlReader.io.pop.payload(SnesButtons.Start)
    ////  //rLedRgb(3) := !snesCtrlReader.io.pop.payload(SnesButtons.Select)
    ////}
    ////when (snesCtrlReader.io.pop.fire) {
    ////  rSnesCtrlReaderPopReady := False
    ////  //rReadSnesButtonsRisingEdge := False
    ////  //rResetReadSnesButtonsRisingEdge := False
    ////}

    ////ctrlIo.en := gradIo.vgaCtrlIo.en
    ////ctrlIo.push << gradIo.vgaCtrlIo.push
    ////ctrlIo.en := True
    ////ctrlIo.push.valid := gpuIo.pop.valid
    ////ctrlIo.push.payload := gpuIo.pop.payload.col
    ////gpuIo.pop.ready := ctrlIo.push.ready
    ////val myGpuPopStm = cloneOf(gpuIo.pop)
    ////clkCtrl.gpuToVgaFifoCC.io.push <-/< myGpuPopStm
    ////clkCtrl.myGpuToVgaFifoPush 
    ////gpuIo.pop.translateInto(into=myGpuPopStm)(
    ////  dataAssignment=(o, i) => {
    ////    o := i.col
    ////  }
    ////)
    //myGpuPopStm <-/< gpuIo.pop
    ////vgaCtrl.io.pixels <-/< myGpuPopStm
    ////--------
    //val gpu2dBlanking = Gpu2dBlanking(
    //  params=gpu2dParams,
    //  vgaTimingInfo=vgaTimingInfo,
    //)
    //myGpuPopStm.translateInto(
    //  //into=vgaCtrl.io.pixels
    //  //into=ctrlIo.push
    //  into=gpu2dBlanking.io.push
    //)(
    //  dataAssignment=(o, i) => {
    //    o := i.col
    //  }
    //)
    //ctrlIo.push <-/< gpu2dBlanking.io.pop
    ////--------
    ////clkCtrl.myGpuToVgaFifoPush <-/< myGpuPopStm
    ////clkCtrl.gpuToVgaFifoCC.io.push <-/< myGpuPopStm

    ////ctrlIo.push <-/< clkCtrl.gpuToVgaFifoCC.io.pop


    ////clkCtrl.gpuToVgaFifoCC.io.push.valid := gpuIo.pop.valid
    ////clkCtrl.gpuToVgaFifoCC.io.push.payload := gpuIo.pop.col
    ////gpuIo.pop.ready := clkCtrl.gpuToVgaFifoCC.io.push.ready

    ////ctrlIo.push.valid := clkCtrl.gpuToVgaFifoCC.io.pop.valid
    ////ctrlIo.push.payload := clkCtrl.gpuToVgaFifoCC.io.pop.payload
    ////clkCtrl.gpuToVgaFifoCC.io.pop.ready := ctrlIo.push.ready

    ////gradIo.vgaCtrlIo.misc := ctrlIo.misc

    ////dithIo.push << gradIo.vidDithIo.push
    //////gradIo.vidDithIo.outp := dithIo.outp
    ////dithIo.pop >> gradIo.vidDithIo.pop 
    ////gradIo.vidDithIo.info := dithIo.info

    ////io.outpVga := vgaCtrl.io.phys
    val myDut = Gpu2dSimDut(
      clkRate=coreClkRate,
      rgbConfig=gpu2dParams.rgbConfig,
      vgaTimingInfo=vgaTimingInfo,
      gpu2dParams=gpu2dParams,
      ctrlFifoDepth=clkCtrl.vgaCtrlFifoDepth,
      optRawSnesButtons=false,
      dbgPipeMemRmw=false,
      vivadoDebug=(
        false
        //true
      ),
    )
    io.snesCtrl <> myDut.io.snesCtrl
    myDut.gpu2d.io.pop.physPosInfo.pos.addAttribute(
      "MARK_DEBUG", "TRUE"
    )

    for (idx <- 0 to io.outpVgaColR.size - 1) {
      //io.outpVgaColR(idx) := ctrlIo.phys.col.r(idx)
      io.outpVgaColR(idx) := myDut.io.phys.col.r(idx)
      //io.outpVgaColR(idx) := ctrlIo.vga.color.r(idx)
      //io.outpLedR(idx) := ctrlIo.phys.col.r(idx)
    }
    for (idx <- 0 to io.outpVgaColG.size - 1) {
      //io.outpVgaColG(idx) := ctrlIo.phys.col.g(idx)
      io.outpVgaColG(idx) := myDut.io.phys.col.g(idx)
      //io.outpVgaColG(idx) := ctrlIo.vga.color.g(idx)
      //io.outpLedG(idx) := ctrlIo.phys.col.g(idx)
    }
    for (idx <- 0 to io.outpVgaColB.size - 1) {
      //io.outpVgaColB(idx) := ctrlIo.phys.col.b(idx)
      io.outpVgaColB(idx) := myDut.io.phys.col.b(idx)
      //io.outpVgaColB(idx) := ctrlIo.vga.color.b(idx)
      //io.outpLedB(idx) := ctrlIo.phys.col.b(idx)
    }

    //io.outpVgaHsync := ctrlIo.phys.hsync
    //io.outpVgaVsync := ctrlIo.phys.vsync
    io.outpVgaHsync := myDut.io.phys.hsync
    io.outpVgaVsync := myDut.io.phys.vsync
    //io.outpVgaHsync := ctrlIo.vga.hSync
    //io.outpVgaVsync := ctrlIo.vga.vSync
  }
  //--------
}

object MyConfig {
  def spinal = SpinalConfig(
    targetDirectory="hw/gen",
    defaultConfigForClockDomains=ClockDomainConfig(
      //resetActiveLevel=HIGH,
      resetKind=BOOT,
    ),
    onlyStdLogicVectorAtTopLevelIo=true,
  )
    //.addStandardMemBlackboxing(blackboxAll)
}

object MyTopLevelVerilog extends App {
  MyConfig.spinal.generateVerilog(MyTopLevel())
}

object MyTopLevelVhdl extends App {
  MyConfig.spinal.generateVhdl(MyTopLevel())
  //val report = SpinalVhdl(new MyTopLevel())
  //report.printPruned()
  //val test = PipeSkidBuf(UInt(3 bits))
}
//object MyTopLevelQuartusParams {
//  val quartusPath = "/home/fl4shk/intelFPGA_lite/20.1/quartus/bin/"
//  //val workspacePath = "/home/fl4shk/Documents/prog/electronics/spinalhdl/flow_projects/altera/tmp"
//  val workspacePath = "/media/other_data/fl4shk_home_stuff/Documents/prog/electronics/spinalhdl/small_projects_and_tests/artyA7100TVgaTest/quartus"
//}
//
//object MyTopLevelQuartusFlow extends App {
//  val report = QuartusFlow(
//    quartusPath=MyTopLevelQuartusParams.quartusPath,
//    workspacePath=MyTopLevelQuartusParams.workspacePath,
//    toplevelPath="MyTopLevel.v",
//    family="Cyclone V",
//    //device="5CEBA4F23C7N",
//    device="5CEBA4F23C7",
//    frequencyTarget=100 MHz,
//    //frequencyTarget=50 MHz,
//    //frequencyTarget=100.7 MHz,
//    processorCount=8,
//  )
//  println(report)
//}
//object MyTopLevelQuartusProject extends App {
//  val prj = new QuartusProject(
//    quartusPath=MyTopLevelQuartusParams.quartusPath,
//    workspacePath=MyTopLevelQuartusParams.workspacePath,
//  )
//  prj.compile()
//  prj.program()
//}
