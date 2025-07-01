# artyA7100TVgaTest

To generate Verilog code from the SpinalHDL code, run
```
sbt "runMain artyA7100TVgaTest.MyTopLevelSnowHouseCpuToVerilog"
```

Following that, you can recreate the Xilinx/AMD Vivado project by running
```
source recreate-vivado-project.tcl
```
from the Vivado TCL console.
