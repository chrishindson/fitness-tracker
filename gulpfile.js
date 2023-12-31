'use strict';
const gulp = require('gulp');
const sass = require('gulp-sass')(require('sass'));
const minify = require('gulp-minify');
const del = require('del');
const rename = require("gulp-rename");
const babel = require('gulp-babel');
const webpack = require("webpack");
const uglifycss = require('gulp-uglifycss');

const environments = require('gulp-environments');
const production = environments.production;

const webpackConfig = "./webpack.config.js";
const paths = {
  styles: {
    src: 'src/main/resources/static/sass/**/*.scss',
    dest: 'src/main/resources/static/css/',
    clear: 'src/main/resources/static/css/*'
  }, scripts: {
    src: 'src/main/resources/static/js/**/*.js',
    es: 'src/main/resources/static/es/',
    dest: 'src/main/resources/static/js/',
    clear: 'src/main/resources/static/js/*'
  }
};

function compile(done) {
  return new Promise((resolve, reject) => {
    webpack(require(webpackConfig), (err, stats) => {
      if (err) {
        return reject(err);
      }
      if (stats.hasErrors()) {
        return reject(new Error(stats));
      }
      resolve();
    });
  });
}

function reload(done) {
  browserSync.reload();
  done();
}

gulp.task('install-jquery', () => {
  return gulp.src('node_modules/jquery/dist/jquery.js')
    .pipe(gulp.dest('src/main/resources/static/es/'));
})

gulp.task('styles', () => {
  return gulp.src("src/main/resources/static/sass/style.scss")
    .pipe(sass().on('error', sass.logError))
    .pipe(gulp.dest([paths.styles.dest]));
});
gulp.task('watch', () => {
  return gulp.watch('src/main/resources/static/sass/*.scss', (done) => {
    watch('./src/main/resources/static/sass/*.scss', (done) => {
      gulp.series(['clean', 'styles', 'copy-sass-and-reload'])(done);
    });
    watch(['./src/main/resources/**/*.html'],
      gulp.series('copy-html-and-reload'));
    watch(['./src/main/resources/**/*.css'],
      gulp.series('copy-css-and-reload'));
    watch(['./src/main/resources/static/js/**/*.js'],
      gulp.series('copy-js-and-reload'));
    watch(['./src/main/resources/static/es/**/*.js'],
      gulp.series('compile', 'scripts', 'copy-js-and-reload'));
  })
})

gulp.task('gov-toolkit-install-scripts', () => {
  return gulp.src('node_modules/govuk-frontend/dist/govuk/all.bundle.js')
    .pipe(rename("gov.js"))
    .pipe(gulp.dest('src/main/resources/static/js/'));
});

gulp.task('nhs-toolkit-install-favicons', () => {
  return gulp.src('node_modules/nhsuk-frontend/packages/assets/favicons/*')
    .pipe(gulp.dest('src/main/resources/static/images/favicons/'));
});

gulp.task('nhs-toolkit-install-icons', () => {
  return gulp.src('node_modules/nhsuk-frontend/packages/assets/icons/*')
    .pipe(gulp.dest('src/main/resources/static/images/icons/'));
});

gulp.task('nhs-toolkit-install-logos', () => {
  return gulp.src('node_modules/nhsuk-frontend/packages/assets/logos/*')
    .pipe(gulp.dest('src/main/resources/static/images/logos/'));
});
gulp.task('nhs-toolkit-install',
  gulp.series('nhs-toolkit-install-favicons', 'nhs-toolkit-install-icons',
    'nhs-toolkit-install-logos'));
gulp.task('clean', () => {
  return del(['src/main/resources/static/css/style.css',
    'src/main/resources/static/assets', 'src/main/resources/static/js/*.js',
    './build/',]);
});
gulp.task('copy-html', function () {
  return gulp.src(['./src/main/resources/**/*.html']).pipe(
    gulp.dest('target/classes/'))
});
gulp.task('copy-css', () => gulp.src(['./src/main/resources/**/*.css']).pipe(
  production(uglifycss())).pipe(gulp.dest('target/classes/')));
gulp.task('copy-js', () => gulp.src(['./src/main/resources/**/*.js'])
  .pipe(gulp.dest('./target/classes/')));

gulp.task('build', gulp.series('copy-html', 'copy-css', 'copy-js'));
gulp.task('copy-html-and-reload', gulp.series('copy-html', reload));
gulp.task('copy-css-and-reload', gulp.series('copy-css', reload));
gulp.task('copy-js-and-reload', gulp.series('copy-js', reload));
gulp.task('copy-sass-and-reload', gulp.series('styles', 'copy-css-and-reload'));
gulp.task('scripts', function () {
  return gulp.src(
    ['./build/*.js'])
    .pipe(babel({
      presets: ['@babel/preset-env']
    }))
    .pipe(rename("application.js"))
    .pipe(gulp.dest('src/main/resources/static/js'));
});
gulp.task('minifyJS', () => {
  return gulp.src('src/main/resources/static/js/*.js')
    .pipe(minify())
    .pipe(gulp.dest('src/main/resources/static/js'))
});
gulp.task('clear-up', () => {
  return del('src/main/resources/static/js/!(*-min).js');
})
gulp.task('compile', compile);
gulp.task('default', gulp.series(
  ['clean', 'install-jquery', 'compile', 'styles', 'scripts',
    'nhs-toolkit-install', 'gov-toolkit-install-scripts', 'minifyJS',
    'clear-up']));