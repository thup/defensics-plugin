/*
 * Copyright © 2020 Synopsys, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.synopsys.defensics.apiserver.model;

import io.crnk.core.resource.annotations.JsonApiField;
import io.crnk.core.resource.annotations.JsonApiRelation;
import io.crnk.core.resource.annotations.JsonApiResource;
import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import java.time.Duration;
import java.time.ZonedDateTime;

/**
 * Information about some past test run. As this describes data about past event, (nearly)
 * everything here should be read-only.
 */
@JsonApiResource(type = "results")
public class Result extends BaseTestRun {

  @Schema(description = "Number of test cases executed", example = "42321")
  @JsonApiField(postable = false, patchable = false)
  private Long testCasesExecuted;

  @Schema(
      description = "Percentage of planned run completed (null for unlimited runs)",
      example = "43.5"
  )
  @JsonApiField(postable = false, patchable = false)
  private BigDecimal completionPercentage;

  @Schema(description = "How run execution ended (finished, not finished))", example = "FINISHED")
  @JsonApiField(postable = false, patchable = false)
  private RunStoppingStatus stoppingStatus;

  @Schema(description = "Total verdict of the test run", example = "FAIL")
  @JsonApiField(postable = false, patchable = false)
  private RunVerdict runVerdict;

  @Schema(description = "When test run ended")
  @JsonApiField(postable = false, patchable = false)
  private ZonedDateTime runEndTime;

  // TODO ideally Duration would get automatically documented properly, and this wouldn't be needed
  @Schema(description = "Run duration in seconds", type = "number", example = "255.421")
  @JsonApiField(postable = false, patchable = false)
  private Duration runDuration;

  @Schema(description = "Information about configuration that was used on the run")
  @JsonApiRelation(mappedBy = "result")
  @JsonApiField(postable = false, patchable = false)
  protected ResultTestConfiguration configuration;

  public Result() {
  }

  /**
   * Constructor.
   *
   * @param id Result ID
   * @param runName Run name
   * @param projectId Project ID
   * @param testCasesExecuted How many cases have been executed
   * @param completionPercentage Completion percentage (check field definition for details)
   * @param stoppingStatus Execution outcome of the run
   * @param runType Run type
   * @param runVerdict Run verdict
   * @param runStartTime Run starting time
   * @param runEndTime Run ending time
   * @param runDuration Run duration
   */
  public Result(String id, String runName, String projectId, Long testCasesExecuted,
      BigDecimal completionPercentage, RunStoppingStatus stoppingStatus, RunType runType,
      RunVerdict runVerdict, ZonedDateTime runStartTime, ZonedDateTime runEndTime,
      Duration runDuration) {
    this.id = id;
    this.runName = runName;
    this.projectId = projectId;
    this.testCasesExecuted = testCasesExecuted;
    this.completionPercentage = completionPercentage;
    this.stoppingStatus = stoppingStatus;
    this.runType = runType;
    this.runVerdict = runVerdict;
    this.runStartTime = runStartTime;
    this.runEndTime = runEndTime;
    this.runDuration = runDuration;
  }

  public Long getTestCasesExecuted() {
    return testCasesExecuted;
  }

  public void setTestCasesExecuted(Long testCasesExecuted) {
    this.testCasesExecuted = testCasesExecuted;
  }

  public BigDecimal getCompletionPercentage() {
    return completionPercentage;
  }

  public void setCompletionPercentage(BigDecimal completionPercentage) {
    this.completionPercentage = completionPercentage;
  }

  public RunStoppingStatus getStoppingStatus() {
    return stoppingStatus;
  }

  public void setStoppingStatus(RunStoppingStatus stoppingStatus) {
    this.stoppingStatus = stoppingStatus;
  }

  public RunVerdict getRunVerdict() {
    return runVerdict;
  }

  public void setRunVerdict(RunVerdict runVerdict) {
    this.runVerdict = runVerdict;
  }

  public ZonedDateTime getRunEndTime() {
    return runEndTime;
  }

  public void setRunEndTime(ZonedDateTime runEndTime) {
    this.runEndTime = runEndTime;
  }

  public Duration getRunDuration() {
    return runDuration;
  }

  public void setRunDuration(Duration runDuration) {
    this.runDuration = runDuration;
  }

  public ResultTestConfiguration getConfiguration() {
    return configuration;
  }

  public void setConfiguration(
      ResultTestConfiguration configuration) {
    this.configuration = configuration;
  }
}
