#!/usr/bin/python3.5

import subprocess

import sys

TEST_BRANCH_NAME = 'test-branch'


def run():
    completed_process = run_process(['git', 'checkout', 'master'])
    if 0 != completed_process.returncode:
        raise Exception('Can not checkout branch master')

    checkout_test_branch()

    while True:
        completed_process = run_process(['git', 'log', '-1', '--pretty=%B'])
        run_maven(completed_process.stdout.decode().strip())

        completed_process = run_process(['git', 'reset', '--hard', 'HEAD^'])
        if 0 != completed_process.returncode:
            print('Reached first commit. All tests passed successfully!')
            break
    run_process(['git', 'checkout', 'master'])
    run_process(['git', 'branch', '-d', TEST_BRANCH_NAME])


def run_process(arguments):
    return subprocess.run(arguments, stdout=subprocess.PIPE, stderr=subprocess.PIPE)


def checkout_test_branch():
    # Ignore failure to delete the test branch
    run_process(['git', 'branch', '-d', TEST_BRANCH_NAME])
    completed_process = run_process(['git', 'checkout', '-b', TEST_BRANCH_NAME])
    if 0 != completed_process.returncode:
        raise Exception('Can not checkout test branch')


def run_maven(message):
    print('Testing commit: %s' % message)
    completed_process = run_process(['mvn', 'clean', 'verify'])
    if 0 != completed_process.returncode:
        print(completed_process.stdout.decode(), file=sys.stderr)
        raise Exception("Maven failed! :'(")


if __name__ == '__main__':
    run()
