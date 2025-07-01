# source my-opt_design-to-place_design.tcl
cd /media/other_data/fl4shk_home_stuff/Documents/prog/electronics/spinalhdl/small_projects_and_tests/artyA7100TVgaTest/vivado-extra
#open_checkpoint synth.dcp

#synth_design
 
###opt_design -directive ExploreWithRemap -aggressive_remap
opt_design -directive ExploreWithRemap
###opt_design -directive Default
###opt_design -directive Explore
####opt_design -directive ExploreSequentialArea
##opt_design -propconst -sweep -resynth_remap -aggressive_remap -bufg_opt -mbufg_opt -shift_register_opt -dsp_register_opt -carry_remap
##opt_design -propconst -sweep -resynth_remap -aggressive_remap -bufg_opt -mbufg_opt -shift_register_opt -dsp_register_opt -carry_remap
##opt_design -muxf_remap -propconst -sweep -resynth_remap -aggressive_remap -bufg_opt -mbufg_opt -shift_register_opt -dsp_register_opt -carry_remap
#opt_design -muxf_remap -propconst -sweep -aggressive_remap -bufg_opt -mbufg_opt -shift_register_opt -dsp_register_opt -carry_remap

###opt_design -retarget -propconst -sweep -control_set_merge 
###opt_design -directive 
###opt_design -directive Default
##opt_design -directive ExploreWithRemap
##opt_design -directive Default

#place_design -directive ExtraTimingOpt
place_design -directive Auto_1
#place_design -directive Auto_2
#place_design -directive Auto_3
##place_design -directive Explore
##place_design -directive AggressiveExplore
##place_design -directive AltSpreadLogic_medium
place_design -directive ExtraNetDelay_high
##place_design -directive SSI_SpreadLogic_high
##place_design -directive SSI_SpreadLogic_low
##place_design -directive ExtraPostPlacementOpt
##place_design -directive AltSpreadLogic_high
##place_design -directive ExtraNetDelay_high


##phys_opt_design -fanout_opt -placement_opt -routing_opt -slr_crossing_opt -restruct_opt -interconnect_retime -lut_opt -casc_opt -cell_group_opt -critical_cell_opt -dsp_register_opt -bram_register_opt -uram_register_opt -bram_enable_opt -shift_register_opt -hold_fix -aggressive_hold_fix -retime -critical_pin_opt -clock_opt -tns_cleanup -sll_reg_hold_fix
##phys_opt_design -fanout_opt -placement_opt -routing_opt -slr_crossing_opt -restruct_opt -interconnect_retime -lut_opt -casc_opt -cell_group_opt -critical_cell_opt -dsp_register_opt -bram_register_opt -uram_register_opt -bram_enable_opt -shift_register_opt -hold_fix -aggressive_hold_fix -retime -critical_pin_opt -clock_opt -sll_reg_hold_fix
#write_checkpoint -force before-phys_opt_design
##phys_opt_design -fanout_opt -placement_opt -routing_opt -slr_crossing_opt -restruct_opt -interconnect_retime -lut_opt -casc_opt -cell_group_opt -critical_cell_opt -dsp_register_opt -bram_register_opt -uram_register_opt -bram_enable_opt -shift_register_opt -hold_fix -aggressive_hold_fix -retime -critical_pin_opt -clock_opt -sll_reg_hold_fix
#
###route_design -directive AggressiveExplore
###route_design -directive NoTimingRelaxation
###route_design -directive MoreGlobalIterations
##route_design -directive HigherDelayCost
##
##phys_opt_design -directive AggressiveExplore
##phys_opt_design -directive AggressiveFanoutOpt
##
##route_design -directive AggressiveExplore
