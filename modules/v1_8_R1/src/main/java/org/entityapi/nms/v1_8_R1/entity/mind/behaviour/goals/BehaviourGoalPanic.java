/*
 * Copyright (C) EntityAPI Team
 *
 * This file is part of EntityAPI.
 *
 * EntityAPI is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * EntityAPI is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with EntityAPI.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.entityapi.nms.v1_8_R1.entity.mind.behaviour.goals;

import net.minecraft.server.v1_8_R1.EntityLiving;
import net.minecraft.server.v1_8_R1.Vec3D;
import org.bukkit.util.Vector;
import org.entityapi.api.entity.ControllableEntity;
import org.entityapi.api.entity.mind.behaviour.BehaviourType;
import org.entityapi.nms.v1_8_R1.NMSEntityUtil;
import org.entityapi.nms.v1_8_R1.RandomPositionGenerator;
import org.entityapi.nms.v1_8_R1.entity.mind.behaviour.BehaviourGoalBase;

public class BehaviourGoalPanic extends BehaviourGoalBase {

    private double panicX;
    private double panicY;
    private double panicZ;
    private double navigationSpeed;

    public BehaviourGoalPanic(ControllableEntity controllableEntity, double navigationSpeed) {
        super(controllableEntity);
        this.navigationSpeed = navigationSpeed;
    }

    @Override
    public BehaviourType getType() {
        return BehaviourType.INSTINCT;
    }

    @Override
    public String getDefaultKey() {
        return "Panic";
    }

    @Override
    public boolean shouldStart() {
        if (this.getHandle().getLastDamager() == null && !this.getHandle().isBurning()) {
            return false;
        } else {
            Vec3D vec3d = RandomPositionGenerator.a(this.getHandle(), 5, 4);

            if (vec3d == null) {
                return false;
            } else {
                this.panicX = vec3d.c;
                this.panicY = vec3d.d;
                this.panicZ = vec3d.e;
                return true;
            }
        }
    }

    @Override
    public boolean shouldContinue() {
        // CraftBukkit start - introduce a temporary timeout hack until this is fixed properly
        if ((this.getHandle().ticksLived - this.getHandle().aK()) > 100) {
            this.getHandle().b((EntityLiving) null);
            return false;
        }
        // CraftBukkit end
        return !NMSEntityUtil.getNavigation(this.getHandle()).g();
    }

    @Override
    public void start() {
        this.getControllableEntity().navigateTo(this.panicX, this.panicY, this.panicZ, this.navigationSpeed > 0 ? this.navigationSpeed : this.getControllableEntity().getSpeed());
    }

    @Override
    public void tick() {

    }
}