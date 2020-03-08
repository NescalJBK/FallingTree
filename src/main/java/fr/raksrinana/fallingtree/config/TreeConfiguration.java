package fr.raksrinana.fallingtree.config;

import com.google.common.collect.Lists;
import net.minecraft.block.Block;
import net.minecraftforge.common.ForgeConfigSpec;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class TreeConfiguration{
	private final ForgeConfigSpec.ConfigValue<List<? extends String>> whitelistedLogs;
	private final ForgeConfigSpec.ConfigValue<List<? extends String>> blacklistedLogs;
	private final ForgeConfigSpec.IntValue maxSize;
	private final ForgeConfigSpec.BooleanValue lavesBreaking;
	private final ForgeConfigSpec.IntValue lavesBreakingForceRadius;
	
	public TreeConfiguration(ForgeConfigSpec.Builder builder){
		whitelistedLogs = builder.comment("Additional list of blocks (those marked with the log tag will already be whitelisted) considered as logs and that will be destroyed all at once").defineList("logs_whitelisted", Lists.newArrayList(), Objects::nonNull);
		blacklistedLogs = builder.comment("List of blocks that should not be considered as logs (this wins over the whitelist)").defineList("logs_blacklisted", Lists.newArrayList(), Objects::nonNull);
		maxSize = builder.comment("The maximum size of a tree. If there's more logs than this value the tree won't be cut.").defineInRange("logs_max_count", 100, 1, Integer.MAX_VALUE);
		lavesBreaking = builder.comment("When set to true, leaves that should naturally break will be broken instantly").define("leaves_breaking", false);
		lavesBreakingForceRadius = builder.comment("Radius to force break leaves. If another tree is still holding the leaves they'll still be broken. If the leaves are persistent (placed by player) they'll also be destroyed. The radius is applied from one of the top most log blocks. break_leaves must be activated for this to take effect.").defineInRange("leaves_breaking_force_radius", 0, 0, 10);
	}
	
	public Stream<Block> getBlacklistedLogs(){
		return blacklistedLogs.get().stream().map(CommonConfig::getBlock).filter(Objects::nonNull);
	}
	
	public int getLavesBreakingForceRadius(){
		return this.lavesBreakingForceRadius.get();
	}
	
	public int getMaxSize(){
		return this.maxSize.get();
	}
	
	public Stream<Block> getWhitelistedLogs(){
		return whitelistedLogs.get().stream().map(CommonConfig::getBlock).filter(Objects::nonNull);
	}
	
	public boolean isLavesBreaking(){
		return this.lavesBreaking.get();
	}
}
