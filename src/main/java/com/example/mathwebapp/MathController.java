package com.example.mathwebapp;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class MathController {

    @GetMapping("/add")
    public double add(@RequestParam double a, @RequestParam double b) {
        return FibonacciCalculator.add(a, b);
    }

    @GetMapping("/subtract")
    public double subtract(@RequestParam double a, @RequestParam double b) {
        return FibonacciCalculator.subtract(a, b);
    }

    @GetMapping("/multiply")
    public double multiply(@RequestParam double a, @RequestParam double b) {
        return FibonacciCalculator.multiply(a, b);
    }

    @GetMapping("/divide")
    public String divide(@RequestParam double a, @RequestParam double b) {
        return FibonacciCalculator.divide(a, b);
    }

    @GetMapping("/fibonacci")
    public List<FibonacciCalculator.CurveSegment> fibonacci(@RequestParam int n) {
        return FibonacciCalculator.calculateIndividualCurves(n);
    }
}
