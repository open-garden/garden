package com.zipc.garden.webplatform.dsl.sc.validation.validators

import com.zipc.garden.webplatform.dsl.sc.sCModel.Environment
import com.zipc.garden.webplatform.dsl.sc.sCModel.SCModelPackage
import com.zipc.garden.webplatform.dsl.sc.validation.AbstractSCModelValidator
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import org.eclipse.xtext.validation.Check
import org.eclipse.xtext.validation.EValidatorRegistrar

class EnvironmentValidator extends AbstractSCModelValidator {

	public static DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("HHmm");

	public static val TIME_OF_DAY_UNDEFINED = 'time_of_day is undefined!'

	public static val TIME_OF_DAY_FORMAT = 'time_of_day is not date in \'hhmm\'!'

	public static val LIGHT_INTENSITY_UNDEFINED = 'light_intensity is undefined!'

	public static val LIGHT_INTENSITY_RANGE = 'light_intensity is not between 0.0 and 1.0!'

	public static val HEIGHT_ANGLE_UNDEFINED = 'height_angle is undefined!'

	public static val HEIGHT_ANGLE_RANGE = 'height_angle is not between 0.0 and 1.0!'

	public static val CLOUD_DENSITY_UNDEFINED = 'cloud_density is undefined!'

	public static val CLOUD_DENSITY_RANGE = 'cloud_density is not between 0.0 and 1.0!'

	public static val RAIN_DENSITY_UNDEFINED = 'rain_density is undefined!'

	public static val RAIN_DENSITY_RANGE = 'rain_density is not between 0.0 and 1.0!'

	public static val FOG_DENSITY_UNDEFINED = 'fog_density is undefined!'

	public static val FOG_DENSITY_RANGE = 'fog_density is not between 0.0 and 1.0!'

	public static val SNOW_DENSITY_UNDEFINED = 'snow_density is undefined!'

	public static val SNOW_DENSITY_RANGE = 'snow_density is not between 0.0 and 1.0!'

	override register(EValidatorRegistrar registrar) {
		// not needed for classes used as ComposedCheck
	}

	@Check(NORMAL)
	def checkLane(Environment environment) {

		val time_of_day = environment.time_of_day;
		if (time_of_day === null || time_of_day.isEmpty) {
			warning(TIME_OF_DAY_UNDEFINED, SCModelPackage.Literals.ENVIRONMENT__TIME_OF_DAY);
		} else {
			try {
				LocalTime.parse(time_of_day, DateTimeFormatter.ofPattern("HHmm"));
			} catch (Exception exception) {
				error(TIME_OF_DAY_FORMAT, SCModelPackage.Literals.ENVIRONMENT__TIME_OF_DAY);
			}
		}

		val light_intensity = environment.light_intensity;
		if (light_intensity === null) {
			warning(LIGHT_INTENSITY_UNDEFINED, SCModelPackage.Literals.ENVIRONMENT__LIGHT_INTENSITY);
		} else if (light_intensity < 0.0 || light_intensity > 1.0) {
			error(LIGHT_INTENSITY_RANGE, SCModelPackage.Literals.ENVIRONMENT__LIGHT_INTENSITY);
		}

		val height_angle = environment.height_angle;
		if (height_angle === null) {
			warning(LIGHT_INTENSITY_UNDEFINED, SCModelPackage.Literals.ENVIRONMENT__HEIGHT_ANGLE);
		} else if (height_angle < 0.0 || height_angle > 1.0) {
			error(LIGHT_INTENSITY_RANGE, SCModelPackage.Literals.ENVIRONMENT__HEIGHT_ANGLE);
		}

		val cloud_density = environment.cloud_density;
		if (cloud_density === null) {
			warning(CLOUD_DENSITY_UNDEFINED, SCModelPackage.Literals.ENVIRONMENT__CLOUD_DENSITY);
		} else if (cloud_density < 0.0 || cloud_density > 1.0) {
			error(CLOUD_DENSITY_RANGE, SCModelPackage.Literals.ENVIRONMENT__CLOUD_DENSITY);
		}

		val rain_density = environment.rain_density;
		if (rain_density === null) {
			warning(RAIN_DENSITY_UNDEFINED, SCModelPackage.Literals.ENVIRONMENT__RAIN_DENSITY);
		} else if (rain_density < 0.0 || rain_density > 1.0) {
			error(RAIN_DENSITY_RANGE, SCModelPackage.Literals.ENVIRONMENT__RAIN_DENSITY);
		}

		val fog_density = environment.fog_density;
		if (fog_density === null) {
			warning(FOG_DENSITY_UNDEFINED, SCModelPackage.Literals.ENVIRONMENT__FOG_DENSITY);
		} else if (fog_density < 0.0 || fog_density > 1.0) {
			error(FOG_DENSITY_RANGE, SCModelPackage.Literals.ENVIRONMENT__FOG_DENSITY);
		}

		val snow_density = environment.snow_density;
		if (snow_density === null) {
			warning(SNOW_DENSITY_UNDEFINED, SCModelPackage.Literals.ENVIRONMENT__SNOW_DENSITY);
		} else if (snow_density < 0.0 || snow_density > 1.0) {
			error(SNOW_DENSITY_RANGE, SCModelPackage.Literals.ENVIRONMENT__SNOW_DENSITY);
		}
	}
}
