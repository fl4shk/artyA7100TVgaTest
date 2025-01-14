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
//case class PipeMemRmwTopLevel(
//  //wordCount: Int=128
//) extends Component {
//  //--------
//  val io = MyTopLevelIo()
//  //--------
//  def wordWidth = 8
//  def wordType() = UInt(wordWidth bits)
//  def wordCount = (
//    //256
//    512
//  )
//  def modStageCnt = (
//    //2
//    1
//  )
//  def forFmax = (
//    true
//    //false
//  )
//  def modType() = SamplePipeMemRmwModType(
//    wordType=wordType(),
//    wordCount=wordCount,
//    modStageCnt=modStageCnt,
//  )
//  //--------
//  def clkRate = 150.0 MHz
//  //--------
//  val clkCtrl = new Area {
//    val pll = new my_core_clk_wiz
//
//    pll.io.clk_in1_0 := clockDomain.readClockWire
//    val coreClockDomain = ClockDomain.internal(
//      name="core",
//      config=ClockDomainConfig(
//        resetKind=BOOT,
//      ),
//      frequency=FixedFrequency(clkRate),
//    )
//    coreClockDomain.clock := pll.io.clk_out1_0
//  }
//  val core = new ClockingArea(clkCtrl.coreClockDomain) {
//    //val pipeMemRmw = PipeMemRmw(
//    //  wordCount=wordCount
//    //)
//    val pipeMemRmw  = PipeMemRmw[
//      UInt,
//      SamplePipeMemRmwModType[UInt],
//      PipeMemRmwDualRdTypeDisabled[UInt],
//    ](
//      wordType=wordType(),
//      wordCount=wordCount,
//      modType=modType(),
//      modStageCnt=modStageCnt,
//      initBigInt=Some({
//        val tempArr = new ArrayBuffer[BigInt]()
//        for (idx <- 0 until wordCount) {
//          tempArr += BigInt(0)
//        }
//        tempArr.toSeq
//        //Array.fill(wordCount)(BigInt(0)).toSeq
//      })
//    )
//
//    def front = pipeMemRmw.io.front
//    def modFront = pipeMemRmw.io.modFront
//    def modBack = pipeMemRmw.io.modBack
//    def back = pipeMemRmw.io.back
//    front.myExt := front.myExt.getZero
//    front.myExt.memAddr.allowOverride
//
//    //assumeInitial(modFront.ready)
//    //anyseq(modFront.ready)
//
//    //assumeInitial(modBack.payload === modBack.payload.getZero)
//    //assumeInitial(modBack.valid === modBack.valid.getZero)
//    //anyseq(modBack.payload)
//    //anyseq(modBack.valid)
//    val modFrontStm = Stream(modType())
//    val modBackStm = Stream(modType())
//    modFrontStm <-/< modFront
//    modFrontStm.translateInto(
//      into=modBackStm
//    )(
//      dataAssignment=(
//        modBackPayload,
//        modFrontPayload,
//      ) => {
//        //modBackPayload.myExt := modFrontPayload.myExt
//        modBackPayload := modFrontPayload
//        modBackPayload.myExt.allowOverride
//        modBackPayload.myExt.modMemWord := (
//          modFrontPayload.myExt.rdMemWord + 0x1
//        )
//      }
//    )
//    //modBack <-/< modBackStm
//    modBack << modBackStm
//
//    //assumeInitial(back.ready)
//    //anyseq(back.ready)
//    back.ready := True
//    val rFrontValid = Reg(Bool()) init(False)
//    val nextPos = DualTypeNumVec2[UInt, UInt](
//      dataTypeX=wordType(),//UInt(PipeMemTest.wordWidth bits),
//      dataTypeY=UInt(log2Up(wordCount) bits),
//    )
//    val rPos = RegNext(nextPos) init(nextPos.getZero)
//    nextPos := rPos
//
//    pipeMemRmw.io.front.valid := rFrontValid
//    //pipeMemRmw.io.front.data := rPos.x
//    pipeMemRmw.io.front.myExt.memAddr := rPos.y
//
//    //pipeMemRmw.io.back.ready := True
//    when (pipeMemRmw.io.back.fire) {
//      //nextPos.x := pipeMemRmw.io.back.sum
//      //def sum = pipeMemRmw.io.back.sum
//      def modMemWord = pipeMemRmw.io.back.myExt.modMemWord
//
//      //--------
//      io.outpVgaColR(0) := modMemWord(0)
//      io.outpVgaColR(1) := modMemWord(1)
//      io.outpVgaColR(2) := modMemWord(2)
//      io.outpVgaColR(3) := modMemWord(3)
//      //--------
//      io.outpVgaColG(0) := modMemWord(4)
//      io.outpVgaColG(1) := modMemWord(5)
//      io.outpVgaColG(2) := modMemWord(6)
//      io.outpVgaColG(3) := modMemWord(7)
//      //--------
//      io.outpVgaColB(0) := modMemWord(0)
//      io.outpVgaColB(1) := modMemWord(2)
//      io.outpVgaColB(2) := modMemWord(4)
//      io.outpVgaColB(3) := modMemWord(6)
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
//object PipeMemRmwTopLevelToVerilog extends App {
//  MyConfig.spinal.generateVerilog(PipeMemRmwTopLevel())
//}
