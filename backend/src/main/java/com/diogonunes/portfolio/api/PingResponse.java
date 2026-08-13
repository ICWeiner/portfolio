package com.diogonunes.portfolio.api;

public record PingResponse(String service, String version, String buildTime, String timestamp) {
}
