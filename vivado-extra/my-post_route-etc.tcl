# source my-post_route-etc.tcl
cd /media/other_data/fl4shk_home_stuff/Documents/prog/electronics/spinalhdl/small_projects_and_tests/artyA7100TVgaTest/vivado-extra

source my-phys_opt_design.tcl
route_design -directive AggressiveExplore
source my-phys_opt_design.tcl
route_design -directive HigherDelayCost
source my-phys_opt_design.tcl
route_design -directive AggressiveExplore
source my-phys_opt_design.tcl
route_design -directive HigherDelayCost
