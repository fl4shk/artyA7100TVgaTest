# source my-phys_opt_design.tcl
#cd /media/other_data/fl4shk_home_stuff/Documents/prog/electronics/spinalhdl/small_projects_and_tests/artyA7100TVgaTest/vivado-extra

#open_checkpoint before-phys_opt_design.dcp

#open_checkpoint before-phys_opt_design.dcp
#phys_opt_design -fanout_opt -placement_opt -routing_opt -slr_crossing_opt -restruct_opt -interconnect_retime -lut_opt -casc_opt -cell_group_opt -critical_cell_opt -dsp_register_opt -bram_register_opt -uram_register_opt -bram_enable_opt -shift_register_opt -hold_fix -aggressive_hold_fix -retime -critical_pin_opt -clock_opt -sll_reg_hold_fix

#phys_opt_design -fanout_opt -placement_opt -slr_crossing_opt -restruct_opt -interconnect_retime -lut_opt -casc_opt -cell_group_opt -critical_cell_opt -dsp_register_opt -bram_register_opt -uram_register_opt -bram_enable_opt -shift_register_opt -hold_fix -aggressive_hold_fix -retime -critical_pin_opt -sll_reg_hold_fix

phys_opt_design -directive RuntimeOptimized
phys_opt_design -directive AlternateReplication
phys_opt_design -directive AlternateFlowWithRetiming
phys_opt_design -directive AddRetime
phys_opt_design -directive AggressiveFanoutOpt
phys_opt_design -directive Default
phys_opt_design -directive Explore
phys_opt_design -directive ExploreWithAggressiveHoldFix
phys_opt_design -directive ExploreWithHoldFix
phys_opt_design -directive AggressiveExplore

#phys_opt_design -directive RuntimeOptimized
#phys_opt_design -directive AlternateReplication
#phys_opt_design -directive AlternateFlowWithRetiming
#phys_opt_design -directive AddRetime
#phys_opt_design -directive AggressiveFanoutOpt
#phys_opt_design -directive Default
#phys_opt_design -directive Explore
#phys_opt_design -directive ExploreWithAggressiveHoldFix
#phys_opt_design -directive ExploreWithHoldFix
#phys_opt_design -directive AggressiveExplore

#route_design -directive HigherDelayCost
#route_design -directive NoTimingRelaxation
#route_design -directive AggressiveExplore
#route_design -directive Explore


##phys_opt_design -directive AggressiveExplore
#
##phys_opt_design -fanout_opt -placement_opt -routing_opt -slr_crossing_opt -restruct_opt -interconnect_retime -lut_opt -casc_opt -cell_group_opt -critical_cell_opt -dsp_register_opt -bram_register_opt -uram_register_opt -bram_enable_opt -shift_register_opt -hold_fix -aggressive_hold_fix -retime -critical_pin_opt -clock_opt -sll_reg_hold_fix
##phys_opt_design -placement_opt -routing_opt -slr_crossing_opt -restruct_opt -interconnect_retime -lut_opt -casc_opt -cell_group_opt -critical_cell_opt -hold_fix -aggressive_hold_fix -retime -critical_pin_opt -clock_opt -sll_reg_hold_fix
#phys_opt_design -directive AggressiveExplore
#phys_opt_design -directive Explore
#phys_opt_design -directive ExploreWithAggressiveHoldFix
#phys_opt_design -directive AggressiveFanoutOpt
#phys_opt_design -directive AddRetime
#phys_opt_design -directive AlternateFlowWithRetiming
#
##route_design -directive AggressiveExplore
##route_design -directive Explore
#route_design -directive NoTimingRelaxation
#
#phys_opt_design -directive AggressiveExplore
#phys_opt_design -directive Explore
#
#route_design -directive AggressiveExplore
#
#phys_opt_design -directive AggressiveExplore
#phys_opt_design -directive Explore
#
#route_design -tns_cleanup
#
##phys_opt_design -directive Explore
##phys_opt_design -directive ExploreWithAggressiveHoldFix
##phys_opt_design -directive AggressiveFanoutOpt
##phys_opt_design -directive AddRetime
##phys_opt_design -directive AlternateFlowWithRetiming
#
##phys_opt_design -fanout_opt -placement_opt -routing_opt -slr_crossing_opt -restruct_opt -interconnect_retime -lut_opt -casc_opt -cell_group_opt -critical_cell_opt -dsp_register_opt -bram_register_opt -uram_register_opt -bram_enable_opt -shift_register_opt -hold_fix -aggressive_hold_fix -retime -critical_pin_opt -clock_opt -sll_reg_hold_fix
