# source do_synth.tcl
cd /media/other_data/fl4shk_home_stuff/Documents/prog/electronics/spinalhdl/small_projects_and_tests/artyA7100TVgaTest/vivado-extra

#reset_msg_config -severity {ERROR} -default_severity
#synth_design -directive AlternateRoutability -flatten_hierarchy rebuilt -gated_clock_conversion off -bufg 12 -retiming -fsm_extraction auto -keep_equivalent_registers -resource_sharing auto -control_set_opt_threshold auto -no_lc -shreg_min_size 10 -cascade_dsp auto
synth_design -directive AlternateRoutability -flatten_hierarchy rebuilt -gated_clock_conversion off -bufg 12 -retiming -fsm_extraction auto -resource_sharing auto -control_set_opt_threshold auto -no_lc -shreg_min_size 10 -cascade_dsp auto
#synth_design -directive PerformanceOptimized -flatten_hierarchy rebuilt -gated_clock_conversion off -bufg 12 -retiming -fsm_extraction auto -keep_equivalent_registers -resource_sharing auto -control_set_opt_threshold auto -no_lc -shreg_min_size 10 -cascade_dsp auto
#synth_design -directive PerformanceOptimized -flatten_hierarchy rebuilt -gated_clock_conversion off -bufg 12 -retiming -fsm_extraction auto -keep_equivalent_registers -resource_sharing auto -control_set_opt_threshold auto -no_lc -shreg_min_size 5 -cascade_dsp auto
