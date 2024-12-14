package com.example.dreamcar.activity

import com.example.dreamcar.R

object Datasource {
    fun carList(): List<Car> {
        return listOf(
            Car(
                "Ferrari", "488 GTB", 2021, 670, R.drawable.ferrari_812,
                "This supercar perfectly combines power and elegance. With 670 horsepower and an impressive aerodynamic design, the Ferrari 488 GTB offers exhilarating acceleration and a driving experience like no other. Perfect for those seeking luxury and excitement at every turn.",
                "A dream to drive!\nAbsolutely stunning design.\nWorth every penny for the thrill."
            ),
            Car(
                "Lamborghini", "Huracán EVO", 2022, 640, R.drawable.lamborghini_huracan,
                "The Huracán EVO, with 640 horsepower, strikes the perfect balance between advanced technology and Lamborghini's iconic design. Its agile handling and all-wheel drive make it the ideal choice for those who want to conquer the roads with style and precision.",
                "Lamborghini nailed it again.\nHandles like a dream on winding roads.\nThe sound of the engine is music to my ears."
            ),
            Car(
                "Porsche", "911 Turbo S", 2020, 640, R.drawable.porsche_911,
                "This Porsche combines heritage and modernity in a unique package. With 640 horsepower, a sophisticated design, and a tech-packed interior, the 911 Turbo S is perfect for those seeking a versatile sports car that shines on both tracks and streets.",
                "A timeless classic.\nPerfect balance between speed and comfort.\nIdeal for daily driving and track days."
            ),
            Car(
                "McLaren", "720S", 2021, 710, R.drawable.mclaren_720s,
                "With 710 horsepower, the McLaren 720S is synonymous with innovation and performance. Its ultralight carbon-fiber chassis and futuristic design make it perfect for speed and luxury enthusiasts.",
                "Unmatched performance on the track.\nThe design is out of this world.\nA true masterpiece of engineering."
            ),
            Car(
                "Aston Martin", "Vantage", 2022, 670, R.drawable.aston_martin_dbs,
                "Boasting 670 horsepower, the Aston Martin Vantage stands out for its timeless design and meticulous engineering. It’s the perfect companion for those looking for a sports car that blends sophistication with a thrilling roar under the hood.",
                "Sophistication and power in one package.\nA head-turner wherever it goes.\nThe sound of the exhaust is incredible."
            ),
            Car(
                "Audi", "R8", 2021, 670, R.drawable.audi_r8,
                "With 670 horsepower and a V10 engine, the Audi R8 delivers a refined and powerful driving experience. Its quattro all-wheel drive system and iconic design make it ideal for those who value performance without compromising comfort.",
                "The V10 engine is a beast.\nLuxurious and sporty at the same time.\nHandles incredibly well in all conditions."
            ),
            Car(
                "BMW", "M8", 2022, 640, R.drawable.bmw_m8,
                "With 640 horsepower and a design that exudes exclusivity, the BMW M8 is the perfect car for those looking to combine luxury with performance. Its high-end interior and impressive acceleration will always leave you wanting more.",
                "The ultimate driving machine.\nLuxury redefined.\nEffortless speed and comfort."
            ),
            Car(
                "Bugatti", "Chiron", 2021, 800, R.drawable.bugatti_chiron,
                "With 800 horsepower and an unmistakable design, the Bugatti Chiron is a car of dreams. Its unparalleled speed and luxury make it the perfect companion for those who want the best in performance and exclusivity.",
                "The pinnacle of automotive engineering.\nA car that defines luxury.\nUnmatched in speed and prestige."
            ),
            Car(
                "Ford", "GT", 2022, 640, R.drawable.ford_gt,
                "Inspired by the racing world, the Ford GT, with 640 horsepower, combines an iconic design with spectacular aerodynamics. Perfect for those looking for a sports car that's as exciting as it is legendary.",
                "A piece of racing history.\nIncredible performance on the track.\nA true icon reborn."
            ),
            Car(
                "Chevrolet", "Corvette", 2021, 670, R.drawable.chevrolet_corvette,
                "With 670 horsepower and a bold design, the Corvette remains a favorite among car enthusiasts. Its value-for-money performance makes it perfect for those seeking power and style without compromising their budget.",
                "The best bang for your buck.\nClassic American muscle.\nAlways a joy to drive."
            ),
            Car(
                "Bugatti", "Divo", 2021, 900, R.drawable.default_car_icon,
                "With 900 horsepower, the Bugatti Divo is not just a car; it's a statement of luxury and power. Ideal for those seeking something truly unique, this supercar elevates the art of driving to an entirely new level.",
                "Exclusive and powerful.\nA car that speaks for itself.\nThe ultimate status symbol."
            )
        )
    }
}
