/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * The OpenSearch Contributors require contributions made to
 * this file be licensed under the Apache-2.0 license or a
 * compatible open source license.
 */

package org.opensearch.plugin.wlm.action;

import org.opensearch.action.ActionRequestValidationException;
import org.opensearch.action.support.clustermanager.ClusterManagerNodeReadRequest;
import org.opensearch.cluster.metadata.WorkloadGroup;
import org.opensearch.core.common.io.stream.StreamInput;
import org.opensearch.core.common.io.stream.StreamOutput;

import java.io.IOException;

/**
 * Request for get WorkloadGroup
 *
 * @opensearch.experimental
 */
public class GetWorkloadGroupRequest extends ClusterManagerNodeReadRequest<GetWorkloadGroupRequest> {
    final String name;

    /**
     * Default constructor for GetWorkloadGroupRequest
     * @param name - name for the WorkloadGroup to get
     */
    public GetWorkloadGroupRequest(String name) {
        this.name = name;
    }

    /**
     * Constructor for GetWorkloadGroupRequest
     * @param in - A {@link StreamInput} object
     */
    public GetWorkloadGroupRequest(StreamInput in) throws IOException {
        super(in);
        name = in.readOptionalString();
    }

    @Override
    public ActionRequestValidationException validate() {
        if (name != null) {
            WorkloadGroup.validateName(name);
        }
        return null;
    }

    @Override
    public void writeTo(StreamOutput out) throws IOException {
        super.writeTo(out);
        out.writeOptionalString(name);
    }

    /**
     * Name getter
     */
    public String getName() {
        return name;
    }
}
