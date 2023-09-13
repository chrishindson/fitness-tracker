const gulp = require('gulp');
const sass = require('gulp-sass')(require('sass'));
const del = require('del');
const cleanCSS = require('gulp-clean-css');
const uglify = require('gulp-uglify');
const uglifycss = require('gulp-uglifycss');
const rename = require('gulp-rename');
const browserSync = require('browser-sync').create();
const webpack = require("webpack");
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
        })
    })
}

function styles() {
    return gulp.src("src/main/resources/static/sass/style.scss")
        .pipe(sass().on('error', sass.logError))
        .pipe(gulp.dest([paths.styles.dest]));
}

function clean() {
    return del([paths.styles.clear, paths.scripts.clear]);
}

function scripts() {
    return gulp.src(paths.scripts.src)
        .pipe(uglify())
        .pipe(gulp.dest(paths.scripts.dest))
        .pipe(browserSync.stream());
}

function serve() {
    browserSync.init({
        server: {
            baseDir: './'
        }
    });

    gulp.watch('src/scss/**/*.scss', styles);
    gulp.watch('src/js/**/*.js', scripts);
    gulp.watch('./*.html').on('change', browserSync.reload);
}

// gulp.task('sass', function () {
//     return gulp.src('./sass/**/*.scss')
//         .pipe(sass({
//             includePaths: 'node_modules'
//         }))
//         .pipe(gulp.dest('./css'));
// });
gulp.task('install-jquery', () => {
    return gulp.src('node_modules/jquery/dist/jquery.js').pipe(gulp.dest(paths.scripts.es))
});
gulp.task('nhs-toolkit-install-scripts', () => {
    return gulp.src('node_modules/nhsuk-frontend/dist/nhsuk.js').pipe(rename("nhs.js")).pipe(gulp.dest(paths.scripts.dest))
});
gulp.task('gov-toolkit-install-scripts', () => {
    return gulp.src('node_modules/govuk-frontend/govuk/all.js').pipe(rename("gov.js")).pipe(gulp.dest(paths.scripts.dest))
});
gulp.task('accessible-autocomplete-install-scripts', () => {
    return gulp.src('node_modules/accessible-autocomplete/dist/accessible-autocomplete.min.js').pipe(gulp.dest(paths.scripts.dest))
});
gulp.task('moj-toolkit-install-scripts', () => {
    return gulp.src('node_modules/@ministryofjustice/frontend/moj/all.js').pipe(rename("moj.js")).pipe(gulp.dest(paths.scripts.dest))
});
gulp.task('compile', compile);
gulp.task('default', gulp.series(clean, 'install-jquery', 'compile', styles, scripts, 'nhs-toolkit-install-scripts', 'gov-toolkit-install-scripts', 'moj-toolkit-install-scripts', 'accessible-autocomplete-install-scripts'));
exports.styles = styles;
exports.scripts = scripts;
exports.serve = serve;