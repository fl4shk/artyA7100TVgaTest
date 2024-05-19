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

case class VgaTestTopLevel(
) extends Component {
  //--------
  val io = MyTopLevelIo(optIncludeSnesCtrl=false)
  //--------
  def clkRate = 150.0 MHz // 640x480@60 (6x pixel clock)
  val vgaTimingInfo = LcvVgaTimingInfoMap.map("640x480@60")
  //--------
  val clkCtrl = new Area {
    val pll = new my_core_clk_wiz
    pll.io.clk_in1_0 := clockDomain.readClockWire
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
  val core = new ClockingArea(clkCtrl.coreClockDomain) {
    //val pixelClk = 25.0 
    ////def pixelClk = 25.175
    //def ctrlFifoDepth = 100
    //val ctrlFifoDepth = 64
    val ctrlFifoDepth = 10
    //val dbgTrigger = Reg(Bool()) init(False) addAttribute("keep")
    //dbgTrigger := True
    //io.outpDbgTrigger0 := dbgTrigger
    val vgaCtrl = LcvVgaCtrl(
      clkRate=clkRate,
      rgbConfig=MyTopLevelIo.physRgbConfig,
      vgaTimingInfo=vgaTimingInfo,
      //timingInfo=LcvVgaTimingInfoMap.map("640x480@60"),
      fifoDepth=ctrlFifoDepth,
      fifoArrRamStyle="distributed",
      //vivadoDebug=vivadoDebug,
    )
    def ctrlIo = vgaCtrl.io
    ctrlIo.en := True
    ctrlIo.push.valid := True
    ctrlIo.push.payload.r := 0xf
    ctrlIo.push.payload.g := 0x8 
    ctrlIo.push.payload.b := 0x0

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
}
object MyVgaConfig {
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

object MyVgaTopLevelVerilog extends App {
  MyVgaConfig.spinal.generateVerilog(VgaTestTopLevel())
}
