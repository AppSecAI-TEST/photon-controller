/*
 * Copyright 2016 VMware, Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License.  You may obtain a copy of
 * the License at http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed
 * under the License is distributed on an "AS IS" BASIS, without warranties or
 * conditions of any kind, EITHER EXPRESS OR IMPLIED.  See the License for the
 * specific language governing permissions and limitations under the License.
 */

package com.vmware.photon.controller.apife.commands.steps;

import com.vmware.photon.controller.api.common.exceptions.ApiFeException;
import com.vmware.photon.controller.apibackend.servicedocuments.ConnectVmToSwitchTask;
import com.vmware.photon.controller.apibackend.tasks.ConnectVmToSwitchTaskService;
import com.vmware.photon.controller.apife.backends.StepBackend;
import com.vmware.photon.controller.apife.backends.clients.HousekeeperXenonRestClient;
import com.vmware.photon.controller.apife.commands.tasks.TaskCommand;
import com.vmware.photon.controller.apife.entities.StepEntity;
import com.vmware.photon.controller.common.clients.exceptions.RpcException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This is an optional step which only happens when an VM is to be
 * created on a virtual network.
 */
public class VmJoinVirtualNetworkStepCmd extends StepCommand {

  private static Logger logger = LoggerFactory.getLogger(VmJoinVirtualNetworkStepCmd.class);

  public VmJoinVirtualNetworkStepCmd(TaskCommand taskCommand, StepBackend stepBackend, StepEntity step) {
    super(taskCommand, stepBackend, step);
  }

  @Override
  protected void execute() throws ApiFeException, InterruptedException, RpcException {
    ConnectVmToSwitchTask startState = new ConnectVmToSwitchTask();

    // The data required in the startState will be filled out in a different CR.

    HousekeeperXenonRestClient housekeeperXenonRestClient = taskCommand.getHousekeeperXenonRestClient();
    housekeeperXenonRestClient.post(ConnectVmToSwitchTaskService.FACTORY_LINK, startState)
        .getBody(ConnectVmToSwitchTask.class);
  }

  @Override
  protected void cleanup() {
  }
}