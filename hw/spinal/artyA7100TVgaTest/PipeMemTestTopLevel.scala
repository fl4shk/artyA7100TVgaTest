//package artyA7100TVgaTest
//
//import spinal.core._
//import spinal.lib._
//
//import libcheesevoyage._
//import libcheesevoyage.general._
//import libcheesevoyage.gfx._
//import libcheesevoyage.hwdev._
//import scala.collection.mutable.ArrayBuffer
//
////case class PipeMemTestTopLevelIo(
////) extends Bundle {
////}
//case class PipeMemTestTopLevel(
//  //wordCount: Int=128
//) extends Component {
//  //--------
//  val io = MyTopLevelIo()
//  //--------
//  def wordCount = 256
//  //--------
//  def clkRate = 150.0 MHz
//  //--------
//  val clkCtrl = new Area {
//    val pll = new core_clk_wiz
//
//    pll.io.clk_in1 := clockDomain.readClockWire
//    val coreClockDomain = ClockDomain.internal(
//      name="core",
//      config=ClockDomainConfig(
//        resetKind=BOOT,
//      ),
//      frequency=FixedFrequency(clkRate),
//    )
//    coreClockDomain.clock := pll.io.clk_out1
//  }
//  val core = new ClockingArea(clkCtrl.coreClockDomain) {
//    val pipeMemTest = PipeMemTest(
//      wordCount=wordCount
//    )
//    val rFrontValid = Reg(Bool()) init(False)
//    val nextPos = DualTypeNumVec2[UInt, UInt](
//      dataTypeX=UInt(PipeMemTest.wordWidth bits),
//      dataTypeY=UInt(log2Up(wordCount) bits),
//    )
//    val rPos = RegNext(nextPos) init(nextPos.getZero)
//    nextPos := rPos
//
//    pipeMemTest.io.front.valid := rFrontValid
//    pipeMemTest.io.front.data := rPos.x
//    pipeMemTest.io.front.addr := rPos.y
//
//    pipeMemTest.io.back.ready := True
//    when (pipeMemTest.io.back.fire) {
//      //nextPos.x := pipeMemTest.io.back.sum
//      def sum = pipeMemTest.io.back.sum
//
//      //--------
//      io.outpVgaColR(0) := sum(0)
//      io.outpVgaColR(1) := sum(1)
//      io.outpVgaColR(2) := sum(2)
//      io.outpVgaColR(3) := sum(3)
//      //--------
//      io.outpVgaColG(0) := sum(4)
//      io.outpVgaColG(1) := sum(5)
//      io.outpVgaColG(2) := sum(6)
//      io.outpVgaColG(3) := sum(7)
//      //--------
//      io.outpVgaColB(0) := sum(0)
//      io.outpVgaColB(1) := sum(2)
//      io.outpVgaColB(2) := sum(4)
//      io.outpVgaColB(3) := sum(6)
//      //--------
//      io.outpVgaHsync := False
//      io.outpVgaVsync := False
//      //--------
//    } otherwise {
//      for (idx <- 0 until MyTopLevelIo.physRgbConfig.rWidth) {
//        io.outpVgaColR(idx) := RegNext(io.outpVgaColR(idx)) init(False)
//      }
//      for (idx <- 0 until MyTopLevelIo.physRgbConfig.gWidth) {
//        io.outpVgaColG(idx) := RegNext(io.outpVgaColG(idx)) init(False)
//      }
//      for (idx <- 0 until MyTopLevelIo.physRgbConfig.bWidth) {
//        io.outpVgaColB(idx) := RegNext(io.outpVgaColB(idx)) init(False)
//      }
//      io.outpVgaHsync := RegNext(io.outpVgaHsync) init(False)
//      io.outpVgaVsync := RegNext(io.outpVgaVsync) init(False)
//    }
//
//    val snes = new Area {
//      val snesHelper = SnesCtrlReaderHelper(
//        clkRate=clkRate,
//      )
//      val rPopReady = Reg(Bool()) init(False)
//      io.snesCtrl <> snesHelper.io.snesCtrl
//      snesHelper.io.pop.ready := rPopReady
//      val rHoldCnt = Reg(ClkCnt(
//        clkRate=clkRate,
//        time=0.01 sec,
//      ))
//      rHoldCnt.init(rHoldCnt.getZero)
//      rHoldCnt.incr()
//      when (!snesHelper.io.pop.fire) {
//        rPopReady := True
//        rFrontValid := False
//      } otherwise { // when (snesHelper.io.pop.fire)
//        rPopReady := False
//        def buttons = snesHelper.io.pop.payload
//        when (!buttons(SnesButtons.A)) {
//          // A button held
//          rFrontValid := True
//          switch (
//            Cat(
//              buttons(SnesButtons.DpadLeft), buttons(SnesButtons.DpadRight)
//            )
//          ) {
//            is (B"01") {
//              // move left
//              nextPos.x := rPos.x - 1
//            }
//            is (B"10") {
//              // move right
//              nextPos.x := rPos.x + 1
//            }
//            default {
//            }
//          }
//          switch (
//            Cat(
//              buttons(SnesButtons.DpadUp), buttons(SnesButtons.DpadDown)
//            )
//          ) {
//            is (B"01") {
//              // move up
//              nextPos.y := rPos.y + 1
//            }
//            is (B"10") {
//              // move down
//              nextPos.y := rPos.y - 1
//            }
//            default {
//            }
//          }
//        }
//      }
//    }
//  }
//}
//
//object PipeMemTestTopLevelToVerilog extends App {
//  MyConfig.spinal.generateVerilog(PipeMemTestTopLevel())
//}
