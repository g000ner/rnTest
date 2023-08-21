package study.rntest.entity.compute;


import study.rntest.entity.point.Dimension;

import javax.persistence.*;
import java.sql.Time;
import java.util.UUID;

@Entity
@Table(name = "compute_results_logs")
public class ComputeResultLog {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID computeId;

    @Column(name = "run_id")
    private UUID runId;

    @Column(name = "compute_start_time")
    private Time computeStartTime;

    @Column(name = "compute_end_time")
    private Time computeEndTime;

    @Column(name = "first_point_descriptor")
    private String firstPointDescriptor;

    @Column(name = "second_point_descriptor")
    private String secondPointDescriptor;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "compute_type")
    private Dimension computeType;

    @Column(name = "distance")
    private double distance;

    public ComputeResultLog() {
    }

    public ComputeResultLog(
            UUID runId,
            Time computeStartTime,
            Time computeEndTime,
            String firstPointDescriptor,
            String secondPointDescriptor,
            Dimension computeType,
            double distance) {
        this.runId = runId;
        this.computeStartTime = computeStartTime;
        this.computeEndTime = computeEndTime;
        this.firstPointDescriptor = firstPointDescriptor;
        this.secondPointDescriptor = secondPointDescriptor;
        this.computeType = computeType;
        this.distance = distance;
    }

    public UUID getComputeId() {
        return computeId;
    }

    public void setComputeId(UUID computeId) {
        this.computeId = computeId;
    }

    public UUID getRunId() {
        return runId;
    }

    public void setRunId(UUID runId) {
        this.runId = runId;
    }

    public Time getComputeStartTime() {
        return computeStartTime;
    }

    public void setComputeStartTime(Time computeStartTime) {
        this.computeStartTime = computeStartTime;
    }

    public Time getComputeEndTime() {
        return computeEndTime;
    }

    public void setComputeEndTime(Time computeEndTime) {
        this.computeEndTime = computeEndTime;
    }

    public String getFirstPointDescriptor() {
        return firstPointDescriptor;
    }

    public void setFirstPointDescriptor(String firstPointDescriptor) {
        this.firstPointDescriptor = firstPointDescriptor;
    }

    public String getSecondPointDescriptor() {
        return secondPointDescriptor;
    }

    public void setSecondPointDescriptor(String secondPointDescriptor) {
        this.secondPointDescriptor = secondPointDescriptor;
    }

    public Dimension getComputeType() {
        return computeType;
    }

    public void setComputeType(Dimension computeType) {
        this.computeType = computeType;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

}
