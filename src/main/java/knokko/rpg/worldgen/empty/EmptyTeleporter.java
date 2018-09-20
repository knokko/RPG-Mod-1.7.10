package knokko.rpg.worldgen.empty;

import net.minecraft.entity.Entity;
import net.minecraft.world.Teleporter;
import net.minecraft.world.WorldServer;

public class EmptyTeleporter extends Teleporter{

	public EmptyTeleporter(WorldServer server) {
		super(server);
	}
	
	public boolean makePortal(Entity entity){
		return false;
	}
}
