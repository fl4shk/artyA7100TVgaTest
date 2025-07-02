#cd vivado-extra
#open_run impl_2

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

phys_opt_design -directive AggressiveExplore

for {set i 0} {$i < 4} {incr i} {
	route_design -directive NoTimingRelaxation
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
}

#route_design -directive NoTimingRelaxation
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
#
#route_design -directive NoTimingRelaxation
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
